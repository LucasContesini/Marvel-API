INSERT INTO `character` (name, description, modified, resource_URI) VALUES
('3-D Man', '', '2014-04-29', 'http://gateway.marvel.com/v1/public/characters/1011334'),
('A-Bomb (HAS)', 'Rick Jones has been Hulk''s best bud since day one, but now he''s more than a friend...he''s a teammate! Transformed by a Gamma energy explosion, A-Bomb''s thick, armored skin is just as strong and powerful as it is blue. And when he curls into action, he uses it like a giant bowling ball of destruction!', '2013-09-18', 'http://gateway.marvel.com/v1/public/characters/1017100'),
('A.I.M', 'AIM is a terrorist organization bent on destroying the world.', '2013-10-17', 'http://gateway.marvel.com/v1/public/characters/1009144');

INSERT INTO `thumbnail` (path, extension, character_id) VALUES
('http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784', 'jpg', 1),
('http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16', 'jpg', 2),
('http://i.annihil.us/u/prod/marvel/i/mg/6/20/52602f21f29ec', 'jpg', 3);

INSERT INTO `comic` (collection_URI, character_id) VALUES
('http://gateway.marvel.com/v1/public/characters/1011334/comics', 1),
('http://gateway.marvel.com/v1/public/characters/1017100/comics', 2),
('http://gateway.marvel.com/v1/public/characters/1009144/comics', 3);

INSERT INTO `series` (collection_URI, character_id) VALUES
('http://gateway.marvel.com/v1/public/characters/1011334/series', 1),
('http://gateway.marvel.com/v1/public/characters/1017100/series', 2),
('http://gateway.marvel.com/v1/public/characters/1009144/series', 3);

INSERT INTO `story` (collection_URI, character_id) VALUES
('http://gateway.marvel.com/v1/public/characters/1011334/stories', 1),
('http://gateway.marvel.com/v1/public/characters/1017100/stories', 2),
('http://gateway.marvel.com/v1/public/characters/1009144/stories', 3);


INSERT INTO `event` (collection_URI, character_id) VALUES
('http://gateway.marvel.com/v1/public/characters/1011334/events', 1),
('http://gateway.marvel.com/v1/public/characters/1017100/events', 2),
('http://gateway.marvel.com/v1/public/characters/1009144/events', 3);


INSERT INTO `item` (name, resource_URI, type, comic_id, event_id, series_id, story_id) VALUES
('Avengers: The Initiative (2007) #14', 'http://gateway.marvel.com/v1/public/comics/21366', NULL, 1, NULL, NULL, NULL),
('Avengers: The Initiative (2007) #14 (SPOTLIGHT VARIANT)', 'http://gateway.marvel.com/v1/public/comics/24571', NULL, 1, NULL, NULL, NULL),
('Hulk (2008) #53', 'http://gateway.marvel.com/v1/public/comics/40632', NULL, 2, NULL, NULL, NULL),
('Hulk (2008) #54', 'http://gateway.marvel.com/v1/public/comics/40630', NULL, 2, NULL, NULL, NULL),
('Ant-Man & the Wasp (2010) #3', 'http://gateway.marvel.com/v1/public/comics/36763', NULL, 3, NULL, NULL, NULL),
('Avengers (1998) #67', 'http://gateway.marvel.com/v1/public/comics/17553', NULL, 3, NULL, NULL, NULL),
('Avengers: The Initiative (2007 - 2010)', 'http://gateway.marvel.com/v1/public/series/1945', NULL, NULL, NULL, 1, NULL),
('Deadpool (1997 - 2002)', 'http://gateway.marvel.com/v1/public/series/2005', NULL, NULL, NULL, 1, NULL),
('FREE COMIC BOOK DAY 2013 1 (2013)', 'http://gateway.marvel.com/v1/public/series/17765', NULL, NULL, NULL, 2, NULL),
('Hulk (2008 - 2012)', 'http://gateway.marvel.com/v1/public/series/3374', NULL, NULL, NULL, 2, NULL),
('Ant-Man & the Wasp (2010 - 2011)', 'http://gateway.marvel.com/v1/public/series/13082', NULL, NULL, NULL, 3, NULL),
('Avengers (1998 - 2004)', 'http://gateway.marvel.com/v1/public/series/354', NULL, NULL, NULL, 3, NULL),
('Cover #19947', 'http://gateway.marvel.com/v1/public/stories/19947', 'cover', NULL, NULL, NULL, 1),
('The 3-D Man!', 'http://gateway.marvel.com/v1/public/stories/19948', 'interiorStory', NULL, NULL, NULL, 1),
('Hulk (2008) #55', 'http://gateway.marvel.com/v1/public/stories/92078', 'cover', NULL, NULL, NULL, 2),
('Interior #92079', 'http://gateway.marvel.com/v1/public/stories/92079', 'interiorStory', NULL, NULL, NULL, 2),
('Avengers and Power Pack Assemble! (2006) #2', 'http://gateway.marvel.com/v1/public/stories/5800', 'cover', NULL, NULL, NULL, 3),
('2 of 4 - 4XLS', 'http://gateway.marvel.com/v1/public/stories/5801', 'interiorStory', NULL, NULL, NULL, 3),
('Secret Invasion', 'http://gateway.marvel.com/v1/public/events/269', NULL, NULL, 1, NULL, NULL);

INSERT INTO `url` (type, url, character_id) VALUES
('detail', 'http://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', 1),
('wiki', 'http://marvel.com/universe/3-D_Man_(Chandler)?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', 1),
('detail', 'http://marvel.com/characters/76/a-bomb?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', 2),
('comiclink', 'http://marvel.com/comics/characters/1017100/a-bomb_has?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', 2),
('detail', 'http://marvel.com/characters/77/aim.?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', 3),
('wiki', 'http://marvel.com/universe/A.I.M.?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', 3);
