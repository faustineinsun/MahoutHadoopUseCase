#! /bin/bash
# clear all the MAC ".DS_Store" in src file recursively
find ../src -name ".DS_Store" -depth -exec rm {} \;

