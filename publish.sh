#!/bin/bash

outdir=ThiagoWilson

if [ ! -d $outdir ]; then
  mkdir $outdir
fi

contents="dpsmeter.jar relatorio.pdf proposta.pdf wowlog.txt"
cp -r $contents $outdir

tar -caf $outdir.tar.gz $outdir

rm -rf $outdir

