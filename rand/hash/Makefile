# Makefile for creating Bard.jar

JAVASRC     = Bard.java Words.java
MAINCLASS   = Bard
CLASSES     = Bard.class Words.class
JARFILE     = Bard.jar 

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	
$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)
	
clean:
	rm -f $(CLASSES) $(JARFILE)

