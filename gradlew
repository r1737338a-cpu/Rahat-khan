#!/bin/sh
if [ -z "$JAVA_HOME" ]; then
  JAVACMD=$(command -v java)
else
  JAVACMD="$JAVA_HOME/bin/java"
fi

if [ -z "$JAVACMD" ]; then
  echo "ERROR: Java not found. Please install Java or set JAVA_HOME."
  exit 1
fi

PRG="$0"
while [ -h "$PRG" ]; do
  ls=$(ls -ld "$PRG")
  link=$(expr "$ls" : '.*-> \(.*\)$')
  if expr "$link" : '/.*' >/dev/null; then
    PRG="$link"
  else
    PRG=$(dirname "$PRG")/"$link"
  fi
done

PRGDIR=$(dirname "$PRG")
exec "$JAVACMD" -jar "$PRGDIR/gradle/wrapper/gradle-wrapper.jar" "$@"
