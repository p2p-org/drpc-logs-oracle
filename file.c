#include "file.h"
#include "common.h"

int file_open(file_t* f, const char* filename, size_t size) {
  int exists = access(filename, F_OK) == 0;
  errno = 0;

  f->fd = open(filename, O_RDWR | O_CREAT, (mode_t)0600);
  if (f->fd < 0) {
    rcl_perror("open file_t");
    return -1;
  }

  if (!exists) {
    if (ftruncate(f->fd, (off_t)size) != 0) {
      rcl_perror("ftruncate file_t");
      return -1;
    }
  }

  f->bytes = size;
  f->locked = false;

  int flags = PROT_READ | PROT_WRITE;
#ifdef MAP_HUGETLB
  flags = flags | MAP_HUGETLB;
#endif

  f->buffer = mmap(NULL, f->bytes, flags, MAP_SHARED, f->fd, 0);
  if (f->buffer == MAP_FAILED) {
    rcl_perror("mmap file_t");
    return -1;
  }

  return 0;
}

int file_lock(file_t* f) {
  if (f->locked)
    return 0;

  f->locked = true;
  return mlock(f->buffer, f->bytes) == 0 ? 0 : -1;
}

int file_unlock(file_t* f) {
  if (!f->locked)
    return 0;

  return munlock(f->buffer, f->bytes) == 0 ? 0 : -1;
}

/*
int file_resize(file_t* f, size_t size) {
  if (size > RCL_FILE_SIZE_RESERVE) {
    return -1;
  }

  f->bytes = size;

  return ftruncate(f->fd, size) == 0 ? 0 : -1;
}
*/

int file_close(file_t* f) {
  munmap(f->buffer, f->bytes);
  return close(f->fd);
}
