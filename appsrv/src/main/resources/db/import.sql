UPDATE hibernate_sequence set next_val = 1000;

insert into db.Shop(shopId, name, contactPerson) values (1, 'Faie', 'Faie-Manager');
insert into db.Shop(shopId, name, contactPerson) values (2, 'Test1', 'Faie-Manager');
insert into db.Shop(shopId, name, contactPerson) values (3, 'Test2', 'Faie-Manager');

insert into db.APILink(apiId, url, description, user, password, shopId) values (1, 'https://www.faie.at/backend/export/index/agraraktionen.csv?feedID=68&hash=1bfdc5718d84ebfd191e9ee6617a7764', 'Faie', '', '', 1);
insert into db.APILink(apiId, url, description, user, password, shopId) values (2, '', '', 'wdawd', 'awd', 2);
insert into db.APILink(apiId, url, description, user, password, shopId) values (3, '', '', 'awd', 'awd', 2);

-- INSERT INTO db.User(email, password, username, loggedIn) VALUES ('g@g.g', '12341234', 'user', false)
