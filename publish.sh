#!/bin/bash

outdir=ThiagoWilson

if [ ! -d $outdir ]; then
  mkdir $outdir
  mkdir $outdir/dpsmeter
fi

contents="relatorio.pdf proposta.pdf wowlog.txt"
cp -r $contents $outdir
cp -r src/ classes/ .project .classpath build.sh download_libs.sh $outdir/dpsmeter

tar -caf $outdir.tar.gz $outdir

rm -rf $outdir

