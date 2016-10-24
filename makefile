ANTLR=lib/antlr-4.5.3-complete.jar
GRAMMAR=Luazinha
SRC=src
OUT=build
TEMP=temp

ARG1=corretorAutomatico/CorretorTrabalho1.jar
ARG2="java -jar $(OUT)/laLexer.jar"
ARG3=gcc
ARG4=temp
ARG5=casosDeTeste
ARG6="407933, 408000, 000000, 000000"


.PHONY: build

default: build run

teste: teste-sintatico

teste-sintatico:
	java -jar $(ARG1) $(ARG2) $(ARG3) $(ARG4)/ $(ARG5) $(ARG6) sintatico

teste-semantico:
	java -jar $(ARG1) $(ARG2) $(ARG3) $(ARG4)/ $(ARG5) $(ARG6) semantico

teste-gerador:
	java -jar $(ARG1) $(ARG2) $(ARG3) $(ARG4)/ $(ARG5) $(ARG6) gerador

teste-tudo:
	java -jar $(ARG1) $(ARG2) $(ARG3) $(ARG4)/ $(ARG5) $(ARG6) tudo

build: clean
	@echo - Build Start -
	mkdir $(OUT)/src
	mkdir $(OUT)/out
	mkdir $(OUT)/cp
	java -jar $(ANTLR) -o $(OUT) -encoding "UTF-8" -no-listener -no-visitor $(SRC)/$(GRAMMAR).g4
	cp \
		$(SRC)/EntradaTabelaDeSimbolos.java \
		$(SRC)/Mensagens.java \
		$(SRC)/PilhaDeTabelas.java \
		$(SRC)/Saida.java \
		$(SRC)/TabelaDeSimbolos.java \
		$(SRC)/TestaAnalisadorSemantico.java \
		-t $(OUT)/src
	cp $(SRC)/casosDeTeste -r -t $(OUT)/src
	mkdir temp/arg
	mv $(OUT)/src/* -t temp/arg
	mkdir $(OUT)/src/trabalho2
	mv temp/arg/* $(OUT)/src/trabalho2
	javac \
		-d $(OUT)/out \
		-cp $(ANTLR) \
		-sourcepath $(OUT)/src \
		$(OUT)/src/trabalho2/*.java
		# -verbose \
	cp $(OUT)/src/**/*[^.java] $(OUT)/out/trabalho2 -r
	@echo - Build End -
	@echo

clean:
	@echo - Clean Start -
	rm $(OUT)/* -rf
	-rm $(ARG4)/* -rf
	@echo - Clean End -
	@echo

run:
	@echo
	java -cp $(ANTLR):build/out/ trabalho2.TestaAnalisadorSemantico
