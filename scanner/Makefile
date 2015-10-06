.PHONY: all clean
CC=gcc
CPP=g++
AR=ar
RANLIB=ranlib
CFLAGS= -g -Wall
C11FLAGS= -g -Wall -std=c++11
SRCDIR = ./src
INCLUDEDIR = -I./include -I.
LIBS = -lm
PROGRAMS = main \
	   host \
	   utils

all: $(PROGRAMS)

main: main.cpp
	$(CPP) $(CFLAGS) -o $@ $^ $(INCLUDEDIR) $(LIBS)
