#include "vector.h"
#include "common.h"

bool vector_init(vector_t* v, uint64_t capacity, uint64_t item_size) {
  if (capacity > 0) {
    v->buffer = calloc(capacity, item_size);
    if (rcl_unlikely(v->buffer == NULL)) {
      return false;
    }
  } else {
    v->buffer = NULL;
  }

  v->size = 0;
  v->item_size = item_size;
  v->capacity = capacity;

  return true;
}

void vector_destroy(vector_t* v) {
  if (v->buffer != NULL)
    free(v->buffer);
}

void* vector_add(vector_t* v) {
  uint64_t old_capacity = v->capacity, new_capacity;

  if (old_capacity == v->size) {
    if (old_capacity < 16) {
      new_capacity = (old_capacity == 0) ? 4 : old_capacity * 2;
    } else {
      new_capacity = old_capacity + old_capacity / 2;
    }

    v->capacity = new_capacity;
    v->buffer = realloc(v->buffer, new_capacity * v->item_size);
    if (rcl_unlikely(v->buffer == NULL)) {
      return NULL;
    }
  }

  void* item = rcl_pointer_to(v->buffer, v->item_size * v->size);
  v->size++;

  return item;
}

void vector_remove(vector_t* v, void* item) {
  uint8_t* last = vector_last(v);

  if (item != last) {
    rcl_memcpy(item, last, v->item_size);
  }

  v->size--;
}
