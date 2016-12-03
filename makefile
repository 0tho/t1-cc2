BASE_DIR=$(shell pwd)
SRC=$(BASE_DIR)/src
BUILD=$(BASE_DIR)/build
DIST=$(BASE_DIR)/dist
TEMP=$(BASE_DIR)/temp
LIBRARIES=$(BASE_DIR)/libs

GRAMMAR=La
PACKAGE=trabalho1

ANTLR=$(LIBRARIES)/antlr-4.5.3-complete.jar

ARG1=corretorAutomatico/CorretorTrabalho1.jar
ARG2="java -cp $(ANTLR):$(DIST) $(PACKAGE).Lac"
ARG3=gcc
ARG4=$(TEMP)
ARG5=casosDeTeste
ARG6="408514, 407933, 408000, 386227"
# sintatico, semantico, gerador, tudo
TEST=sintatico

# Argumentos para teste caso a caso
CASE=1

.PHONY: build

default: build test

test:
	@mkdir -p $(TEMP)
	@java -jar $(ARG1) $(ARG2) $(ARG3) $(ARG4)/ $(ARG5) $(ARG6) $(TEST)


testn:
	@mkdir -p $(TEMP)
	@java -jar $(ARG1) $(ARG2) $(ARG3) $(ARG4)/ $(ARG5) $(ARG6) $(TEST) $(CASE)

build: clean
	mkdir -p $(BUILD)
	mkdir -p $(DIST)
	@echo - Build Start -
	cd $(SRC); \
		java \
			-jar $(ANTLR) \
			-o $(BUILD)/$(PACKAGE) \
			-encoding "UTF-8" \
			-no-listener \
      -visitor \
			-package $(PACKAGE) \
			$(SRC)/$(PACKAGE)/$(GRAMMAR)Lexer.g4
	cd $(SRC); \
		java \
			-jar $(ANTLR) \
			-o $(BUILD)/$(PACKAGE) \
			-encoding "UTF-8" \
			-no-listener \
			-visitor \
			-package $(PACKAGE) \
			$(SRC)/$(PACKAGE)/$(GRAMMAR)Parser.g4
	cd $(SRC); cp --parents \
		**/*.java \
		-t $(BUILD)
	javac \
		-d $(DIST) \
		-cp $(ANTLR) \
		-sourcepath $(BUILD)/src \
		$(BUILD)/**/*.java
	@echo - Build End -
	@echo

clean:
	@echo - Clean Start -
	rm $(BUILD)/* -rf
	rm $(TEMP)/* -rf
	rm $(DIST)/* -rf
	@echo - Clean End -
	@echo
