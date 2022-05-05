#! /bin/bash

cd ../..
rm -rf assets data app.js index.html
cd src
mkdir src/assets
touch src/assets/temp
npm run build
cp -rp dist/* ..
rm -rf dist ../assets src/assets
