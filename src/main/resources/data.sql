INSERT INTO `character` (name, description, modified, resource_URI) VALUES
('3-D Man', '', '2014-04-29T14:18:17.0400', 'http://gateway.marvel.com/v1/public/characters/1011334');

INSERT INTO `thumbnail` (path, extension, character_id) VALUES
('http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784', 'jpg', 1);

INSERT INTO `comic` (collection_URI, character_id) VALUES
('http://gateway.marvel.com/v1/public/characters/1011334/comics', 1);

INSERT INTO `series` (collection_URI, character_id) VALUES
('http://gateway.marvel.com/v1/public/characters/1011334/series', 1);

INSERT INTO `story` (collection_URI, character_id) VALUES
('http://gateway.marvel.com/v1/public/characters/1011334/stories', 1);

INSERT INTO `event` (collection_URI, character_id) VALUES
('http://gateway.marvel.com/v1/public/characters/1011334/events', 1);

INSERT INTO `item` (name, resource_URI, type, comic_id, event_id, series_id, story_id) VALUES
('Avengers: The Initiative (2007) #14', 'http://gateway.marvel.com/v1/public/comics/21366', NULL, 1, NULL, NULL, NULL),
('Avengers: The Initiative (2007) #14 (SPOTLIGHT VARIANT)', 'http://gateway.marvel.com/v1/public/comics/24571', NULL, 1, NULL, NULL, NULL),
('Avengers: The Initiative (2007 - 2010)', 'http://gateway.marvel.com/v1/public/series/1945', NULL, NULL, NULL, 1, NULL),
('Deadpool (1997 - 2002)', 'http://gateway.marvel.com/v1/public/series/2005', NULL, NULL, NULL, 1, NULL),
('Cover #19947', 'http://gateway.marvel.com/v1/public/stories/19947', 'cover', NULL, NULL, NULL, 1),
('The 3-D Man!', 'http://gateway.marvel.com/v1/public/stories/19948', 'interiorStory', NULL, NULL, NULL, 1),
('Secret Invasion', 'http://gateway.marvel.com/v1/public/events/269', NULL, NULL, 1, NULL, NULL);

INSERT INTO `url` (type, url, character_id) VALUES
('detail', 'http://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', 1),
('wiki', 'http://marvel.com/universe/3-D_Man_(Chandler)?utm_campaign=apiRef&utm_source=67cfd5006b16b8f43fad2a87bf79d002', 1);
