#!/bin/sh

set -e

ROOTDIR=$(dirname $(dirname $0))
SRCFILE=target/service/kodamacast.js
DSTNAME=bundle

if [ -f $DSTNAME.zip ]; then
  echo 'Cleaning existing zip file ...'
  rm $DSTNAME.zip
fi

echo 'Copying necessary files ...'
if [ ! -d $DSTNAME ]; then
  mkdir $DSTNAME
fi
cp $SRCFILE $DSTNAME/index.js
if [ ! -d $DSTNAME/node_modules ]; then
  cp -r $ROOTDIR/node_modules $DSTNAME
fi

echo 'Compressing files ...'
cd $DSTNAME
zip -r ../$DSTNAME.zip * > /dev/null

echo 'Done.'
