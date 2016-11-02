#
# 'make depend' uses makedepend to automatically generate dependencies 
#               (dependencies are added to end of Makefile)
# 'make'        build executable file 'mycc'
# 'make clean'  removes all .o and executable files
#

# define the C compiler to use
CC = g++

SRCDIR = src
BUILDDIR = build
TARGET = bin
MAIN = $(TARGET)/executable

SRCEXT = cpp
SOURCES = $(shell find $(SRCDIR) -type f -name *.$(SRCEXT))
# define the C object files 
#
# This uses Suffix Replacement within a macro:
#   $(name:string1=string2)
#         For each word in 'name' replace 'string1' with 'string2'
# Below we are replacing the suffix .cpp of all words in the macro SRCS
# with the .o suffix
#
OBJECTS = $(patsubst $(SRCDIR)/%,$(BUILDDIR)/%,$(SOURCES:.$(SRCEXT)=.o))
# define any compile-time flags
CFLAGS = -Wall -g
INCLUDES = -I include
# define library paths in addition to /usr/lib
#   if I wanted to include libraries not in /usr/lib I'd specify
#   their path using -Lpath, something like:
LFLAGS = -L lib
# define any libraries to link into executable:
#   if I want to link in libraries (libx.so or libx.a) I use the -llibname 
#   option, something like (this will link in libmylib.so and libm.so:
# 	-lmylib -lm
LIBS = # -llibname

#
# The following part of the makefile is generic; it can be used to 
# build any executable just by changing the definitions above and by
# deleting dependencies appended to the file from 'make depend'
#

$(MAIN): $(OBJECTS)
	@echo "Linking..."
	@echo "$(CC) $^ -o $(MAIN) $(LFLAGS) $(LIBS)"; $(CC) $^ -o $(MAIN) $(LFLAGS) $(LIBS)

$(BUILDDIR)/%.o: $(SRCDIR)/%.$(SRCEXT)
	@echo "Creating object files..."
	@mkdir -p $(BUILDDIR)
	@echo "$(CC) $(CFLAGS) $(INCLUDES) -c -o $@ $<"; $(CC) $(CFLAGS) $(INCLUDES) -c -o $@ $<

depend: $(SRCS)
	make $(INCLUDES) $^

clean:
	@echo "Cleaning..."
	@echo "$(RM) -r $(BUILDDIR) $(TARGET)/*"; $(RM) -r $(BUILDDIR) $(TARGET)/*

# Tests
tester:
	@echo "Building tester..."
	@echo "$(CC) $(CFLAGS) test/tester.cpp $(INCLUDES) $(LFLAGS) $(LIBS) -o bin/tester"; $(CC) $(CFLAGS) test/tester.cpp $(INCLUDES) $(LFLAGS) $(LIBS) -o bin/tester
	

# Spikes
ticket:
	@echo "Building ticket..."
	@echo "$(CC) $(CFLAGS) spike/ticket.cpp $(INCLUDES) $(LFLAGS) $(LIBS) -o bin/ticket"; $(CC) $(CFLAGS) spike/ticket.cpp $(INCLUDES) $(LFLAGS) $(LIBS) -o bin/ticket
	

.PHONY: clean

# DO NOT DELETE THIS LINE -- make depend needs it
