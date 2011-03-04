#/bin/sh

N=/toto

fail () {
  echo $@
  ./sem_unlink $N 2>/dev/null
  ./shm_unlink $N 2>/dev/null
  exit -1
}

# making the executables
make -q all

# cleaning up, just in case
./sem_unlink $N 2>/dev/null
./shm_unlink $N 2>/dev/null

# doing the tests
./sem_create $N                || fail sem_create failed
./sem_create $N 2>/dev/null    && fail sem_create existing didn\'t fail
./sem_getvalue $N >/dev/null   || fail sem_getvalue failed
./sem_post $N                  || fail sem_post failed
./sem_wait $N                  || fail sem_wait failed
(sleep 1; ./sem_post $N) & \
    ./sem_wait $N              || fail sem_wait failed
./sem_unlink $N                || fail sem_unlink failed
./sem_unlink $N 2>/dev/null    && fail sem_unlink non-existing didn\'t fail
./sem_post $N 2>/dev/null      && fail sem_post non-existing didn\'t fail
./sem_wait $N 2>/dev/null      && fail sem_wait non-existing didn\'t fail

./shm_create $N                || fail shm_create failed
./shm_create $N 2>/dev/null    && fail shm_create existing didn\'t fail
(echo hello | ./shm_write $N)  || fail shm_write failed
[ "`./shm_read $N`" == hello ] || fail shm_read failed
./shm_upper10 $N               || fail shm_upper10 failed
[ "`./shm_read $N`" == HELLO ] || fail shm_upper10 didn\`t work
./shm_unlink $N                || fail shm_unlink failed
./shm_unlink $N 2>/dev/null    && fail shm_unlink non-existing didn\'t fail
./shm_read $N 2>/dev/null      && fail shm_read non-existing didn\'t fail
./shm_write $N 2>/dev/null     && fail shm_write non-existing didn\'t fail

echo all tests passed
