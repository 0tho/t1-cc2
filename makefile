ARG1=CorretorTrabalho1/CorretorTrabalho1.jar
ARG2="java -jar $(OUT)/laLexer.jar"
ARG3=gcc
ARG4=temp
ARG5=casosDeTesteT1
ARG6="407933, 408000, 000000, 000000"
ANTLR=antlr-4.5.3-complete.jar
GRAMMAR=Luazinha
GRAMMAR_LOCATION=t2-cc1/
OUT=build

.PHONY: build

all: build teste

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
	mkdir $(OUT)/src
	mkdir $(OUT)/out
	mkdir $(OUT)/cp
	java -jar $(ANTLR) -o $(OUT) -encoding "UTF-8" -no-listener -no-visitor $(GRAMMAR_LOCATION)$(GRAMMAR).g4
	mv $(OUT)/$(GRAMMAR_LOCATION)/* -t $(OUT)/src
	rm $(OUT)/$(GRAMMAR_LOCATION) -r
	cp $(GRAMMAR_LOCATION)EntradaTabelaDeSimbolos.java \
		$(GRAMMAR_LOCATION)Mensagens.java \
		$(GRAMMAR_LOCATION)PilhaDeTabelas.java \
		$(GRAMMAR_LOCATION)Saida.java \
		$(GRAMMAR_LOCATION)TabelaDeSimbolos.java \
		$(GRAMMAR_LOCATION)TestaAnalisadorSemantico.java \
		-t $(OUT)/src
	cp $(GRAMMAR_LOCATION)casosDeTeste -r -t $(OUT)/src
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
	java -cp antlr-4.5.3-complete.jar:build/out/ trabalho2.TestaAnalisadorSemantico 

clean:
	rm $(OUT)/* -rf
	-rm $(ARG4)/* -rf
