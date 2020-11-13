# Makefile for creating ChessMoves.jar

JAVASRC    = ChessMoves.java LinkedList.java Queen.java King.java Rook.java Bishop.java Knight.java Pawn.java ChessPiece.java
MAINCLASS  = ChessMoves
CLASSES    = ChessMoves.class LinkedList.class Queen.class King.class Rook.class Bishop.class Knight.class Pawn.class ChessPiece.class
JARFILE    = ChessMoves.jar

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)
