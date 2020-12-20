INSERT INTO `comic` (description, diamond_code, digital_id, ean, format, isbn, issn, issue_number, modified, page_count, resource_URI, title, upc, variant_description) VALUES
('description', '123', 123, 'ean', 'format', 'isbn', 'issn', 10, '2020-01-01', 123, 'http://gateway.marvel.com/v1/public/characters/1011334/comics', 'title', 'upc', 'variantDescription');

INSERT INTO `creator` (name, role, resource_URI) VALUES
('name', 'role', 'http://gateway.marvel.com/v1/public/stories/47184/creators'),
('name2', 'role2', 'http://gateway.marvel.com/v1/public/stories/47184/creators');

INSERT INTO `creator_comic` (creator_id, comic_id) VALUES
(1, 1),
(2, 1);

INSERT INTO `character` (name, description, modified, resource_URI) VALUES
('3-D Man', '', '2014-04-29', 'http://gateway.marvel.com/v1/public/characters/1011334'),
('A-Bomb (HAS)', 'Rick Jones has been Hulk''s best bud since day one, but now he''s more than a friend...he''s a teammate! Transformed by a Gamma energy explosion, A-Bomb''s thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction!', '2013-09-18', 'http://gateway.marvel.com/v1/public/characters/1017100'),
('A.I.M', 'AIM is a terrorist organization bent on destroying the world.', '2013-10-17', 'http://gateway.marvel.com/v1/public/characters/1009144');

INSERT INTO `character_comic` (comic_id, character_id) VALUES
(1, 1),
(1, 2);

INSERT INTO `series` (description, end_year, start_year, modified, rating, resource_URI, title) VALUES
('description', 2020, 2019, '2020-02-01', 'rating', 'http://gateway.marvel.com/v1/public/characters/1011334/series', 'title'),
('description2', 2020, 2019, '2020-01-10', 'rating2', 'http://gateway.marvel.com/v1/public/characters/1011334/series', 'title2'),
('description3', 2020, 2019, '2020-01-15', 'rating3', 'http://gateway.marvel.com/v1/public/characters/1011334/series', 'title3');

INSERT INTO `character_series` (character_id, series_id) VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO `story` (description, modified, resource_URI, title, type) VALUES
('description', '2020-02-10', 'http://gateway.marvel.com/v1/public/characters/1011334/stories', 'title', 'type'),
('description2', '2020-01-11', 'http://gateway.marvel.com/v1/public/characters/1011334/stories', 'title2', 'type2'),
('description3', '2020-01-15', 'http://gateway.marvel.com/v1/public/characters/1011334/stories', 'title3', 'type3');

INSERT INTO `character_story` (character_id, story_id) VALUES
(1, 1),
(1, 2),
(3, 3);

INSERT INTO `event` (description, `end`, start, modified, resource_URI, title) VALUES
('description', '2020-01-02', '2020-02-01', '2020-11-01', 'http://gateway.marvel.com/v1/public/characters/1011334/events', 'title'),
('description2', '2020-02-02', '2020-03-01', '2020-01-01', 'http://gateway.marvel.com/v1/public/characters/1011334/events', 'title2'),
('description3', '2020-03-02', '2020-04-01', '2020-04-01', 'http://gateway.marvel.com/v1/public/characters/1011334/events', 'title3'),
('description4', '2020-03-02', '2020-04-01', '2020-04-01', 'http://gateway.marvel.com/v1/public/characters/1011334/events', 'title4');

UPDATE `event` SET previous_id = 1 WHERE id = 2;
UPDATE `event` SET previous_id = 2 WHERE id = 3;
UPDATE `event` SET next_id = 2 WHERE id = 1;
UPDATE `event` SET next_id = 3 WHERE id = 2;

INSERT INTO `character_event` (character_id, event_id) VALUES
(1, 1),
(1, 4),
(2, 2),
(3, 3);

INSERT INTO `thumbnail` (path, extension, character_id, event_id) VALUES
('http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784', 'jpg', 1, null),
('http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16', 'jpg', 2, null),
('http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec', 'jpg', 3, null),
('http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec', 'jpg', null, 1),
('http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec', 'jpg', null, 2),
('http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec', 'jpg', null, 3);

INSERT INTO `url` (type, url, character_id, event_id) VALUES
('detail', 'http://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', 1, null),
('wiki', 'http://marvel.com/universe/3-D_Man_(Chandler)?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', 1, null),
('detail', 'http://marvel.com/characters/76/a-bomb?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', 2, null),
('comiclink', 'http://marvel.com/comics/characters/1017100/a-bomb_has?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', 2, null),
('detail', 'http://marvel.com/characters/77/aim.?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', 3, null),
('wiki', 'http://marvel.com/universe/A.I.M.?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', 3, null),
('wiki', 'http://marvel.com/universe/A.I.M.?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', null, 1),
('wiki', 'http://marvel.com/universe/A.I.M.?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', null, 1),
('wiki', 'http://marvel.com/universe/A.I.M.?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', null, 2),
('wiki', 'http://marvel.com/universe/A.I.M.?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', null, 3);
