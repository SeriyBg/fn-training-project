# FN Training Project

#How to generate java class from Protocol Buffer file
export FNSRN_PROTO_SRC_DIR=/media/okolka/sdb/pet/java/training/fn-training-project/src/main/java/com/training/proto
export FNSRN_PROTO_DST_DIR=/media/okolka/sdb/pet/java/training/fn-training-project/src/main/java/
protoc -I=$FNSRN_PROTO_SRC_DIR --java_out=$FNSRN_PROTO_DST_DIR $FNSRN_PROTO_SRC_DIR/host_proto.proto
