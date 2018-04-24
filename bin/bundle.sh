#!/bin/sh

set -e

ROOTDIR=$(cd $(dirname $(dirname $0)) && pwd)
TARGET=$1
TARGETDIR=$ROOTDIR/$TARGET
SRCFILE=$TARGETDIR/target/kodamacast-$TARGET.js
TMPDIR=$(mktemp -d -t $TARGET)
DSTFILE=$ROOTDIR/$TARGET.zip

echo 'Copying necessary files ...'
cp $SRCFILE $TMPDIR/index.js
cp -r $TARGETDIR/node_modules $TMPDIR

echo 'Compressing files ...'
cd $TMPDIR
zip -r $DSTFILE * > /dev/null

echo 'Done.'
