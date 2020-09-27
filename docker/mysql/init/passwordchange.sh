echo "alter user"
echo "ALTER USER 'user'@'%' IDENTIFIED WITH mysql_native_password BY 'password';"|mysql -uroot --password=password --database=mysql 
echo "done alter user..."
