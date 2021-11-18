#!/bin/bash

#for mac
brew install java
echo 'export PATH="/usr/local/opt/openjdk/bin:$PATH"' >> ~/.zshrc
echo 'type source ~/.zshrc'

brew install maven

echo 'run mvn'
