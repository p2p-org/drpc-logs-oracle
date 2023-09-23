# DRPC Logs Oracle ![Test](https://github.com/p2p-org/drpc-logs-oracle/actions/workflows/test.yml/badge.svg)

It's a cache answering the question "how many logs will be in the request to the node".

## System Requirements

- 64-bit architecture, AVX2/3 will give a good performance boost;
- RAM >=16GB, latency is proportional to the amount of memory;
- SSD or NVMe >=128GB, do not recommend HDD;

## Usage

**Local**:

Install dependencies: [simde](https://github.com/simd-everywhere/simde), [RoaringBitmap](https://github.com/RoaringBitmap/CRoaring), [zstd](https://github.com/facebook/zstd).

Build (required go >= 1.20 and GCC / Clang):
- `go mod download`
- `go run . [OPTIONS]`

**With docker**:
```sh
docker build \
  --build-arg="UID=$(id -u)" --build-arg="GID=$(id -g)" \
  -t drpc-logs-oracle .

mkdir -m=0600 ~/.local/share/drpc-logs-oracle # dir for storage
docker run \
  -p 8000:8000 \
  --ulimit memlock=-1:-1 \
  -v ~/.local/share/drpc-logs-oracle:/home/nonroot/data \
  -e ORACLE_DATA_DIR="/home/nonroot/data" \
  -e ORACLE_NODE_ADDR="..." \
  drpc-logs-oracle
```

## Options

Use environment variables for configuration:

```
ORACLE_ENV string (default "production")
  enironment tag, 'development' or 'production'

BIND_PORT int (default ":8000")
  port to RPC server

METRICS_PORT int (default ":8001")
  port to RPC server

ACCESS_LOG bool (default "false")
  enable access logs

DATA_DIR string
  dir for save data

RAM_LIMIT string (default "16GB")
  RAM limit for disk cache

NODE_ADDR string
  ethereum json rpc endpoint url

NODE_BATCH uint (default 512)
  count of block for one request to node
```
