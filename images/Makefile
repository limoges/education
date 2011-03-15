VERSION_MAJOR = 0
VERSION_MINOR = 0
VERSION_REALEASE = 0
VERSION = ${VERSION_MAJOR}.${VERSION_MINOR}.${VERSION_RELEASE}
NAME = "project"
# To use, just run :
# 
# 	make
#
# This will compile the program and output it in the DEBUG directory

# C Flags
CFLAGS +=
# Flags
CPPFLAGS += -Wall
# C++ Flags
CXXFLAGS +=


UNAME := $(shell uname)
ifeq ($(UNAME), Linux) # LINUX-BASED
# nothing specific to be done
endif
ifeq ($(UNAME), Darwin) # DARWIN-BASED (OSX)
CPPFLAGS += -framework OPENGL -framework GLUT
endif

GL = main.cpp

all:opengl

opengl:
	$(CXX) $(CPPFLAGS) $(CXXFLAGS) $(GL)

#%:%.cpp
#	$(CXX) $(CPPFLAGS) $(CXXFLAGS) "$<" -o "$@"
clean:
	rm *.o
archive:
	tar czf $(NAME)-$(VERSION).tar.gz *.c *.h *.cpp Makefile README
