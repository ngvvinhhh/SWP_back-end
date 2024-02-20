echo "Building app..."
./mvnw clean install

echo "Deploy files to server..."
scp -r  target/be.jar root@128.199.253.23:/var/www/be

ssh  root@128.199.253.23 <<EOF
pid=\$(sudo lsof -t -i :8081)

if [ -z "\$pid" ]; then
    echo "Start server..."
else
    echo "Restart server..."
    sudo kill -9 "\$pid"
fi
cd /var/www/be
java -jar be.jar
EOF
exit
echo "Done!"