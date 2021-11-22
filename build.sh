# Please see this post:
# https://stackoverflow.com/questions/67001968/how-to-disable-maven-blocking-external-http-repositores

# -o builds in offline mode so it doesn't redownload. If you add a new pacakge.. run this without the -o
mvn package -o
