# Makefile for creating NQueens.jar

JAVASRC    = NQueens.java StackVars.java
MAINCLASS  = NQueens
CLASSES    = NQueens.class StackVars.class
JARFILE    = NQueens.jar

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	
$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)
	
clean:
	rm -f $(CLASSES) $(JARFILE)

