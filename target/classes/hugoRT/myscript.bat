cd C:\Users\wenci\eclipse-workspace\hugoRT\src\hugoRT
spin -a model.pml
gcc -DMEMLIM=1024 -O2 -DXUSAFE -w -o pan pan.c
pan -m10000  -a -n -c1 -N formula1