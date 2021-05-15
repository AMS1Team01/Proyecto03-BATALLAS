use batallas;
select * from weapons;
insert into warriors values ( 1, "Pepe", "c://path/imagenes/humano01", 1 );

/* Insercion de datos a tabla race*/
insert into race values ( 1, "Human", 50, 5, 5, 6, 3);
insert into race values ( 2, "Elf", 40, 4, 7, 7, 2);
insert into race values ( 3, "Dwarf", 60, 6, 3, 5, 2);

/* Insercion de datos a tabla warriors*/
/*warrior_id, warrior_name, warrior_image_path, race_id*/
insert into warriors values ( 1, "NombreGuerrero1", "c://path/imagenes/humano01", 1);
insert into warriors values ( 2, "NombreGuerrero2", "c://path/imagenes/humano02", 1);
insert into warriors values ( 3, "NombreGuerrero3", "c://path/imagenes/humano03", 1);
insert into warriors values ( 4, "NombreGuerrero4", "c://path/imagenes/elfo01", 2);
insert into warriors values ( 5, "NombreGuerrero5", "c://path/imagenes/elfo02", 2);
insert into warriors values ( 6, "NombreGuerrero6", "c://path/imagenes/elfo03", 2);
insert into warriors values ( 7, "NombreGuerrero7", "c://path/imagenes/enano01", 3);
insert into warriors values ( 8, "NombreGuerrero8", "c://path/imagenes/enano02", 3);
insert into warriors values ( 9, "NombreGuerrero9", "c://path/imagenes/enano03", 3);

/* Insercion de datos a tabla weapons*/
/*weapon_id, weapon_name, weapon_image_path, strength, speed, weapon_race*/
insert into weapons values ( 1, "Dagger", "c://path/imagenes/daga", 3, 0, "Human,Elf");
insert into weapons values ( 2, "Sword", "c://path/imagenes/Espada", 1, 1, "Human,Elf,Dwarf");
insert into weapons values ( 3, "Axe", "c://path/imagenes/Hacha", 3, 0, "human,Dwarf");
insert into weapons values ( 4, "DoubleSwords", "c://path/imagenes/Doubles", 2, 2, "Human,Elf");
insert into weapons values ( 5, "Scimitar", "c://path/imagenes/Cimitarra", 2, 1, "Human,Elf");
insert into weapons values ( 6, "Bow", "c://path/imagenes/Arco", 5, 1, "Elf");
insert into weapons values ( 7, "Katana", "c://path/imagenes/Katana", 3, 2, "Human");
insert into weapons values ( 8, "Knife", "c://path/imagenes/Cuchillo", 4, 0, "Human,Elf,Dwarf");
insert into weapons values ( 9, "Great Axe", "c://path/imagenes/Gran_hacha", 5, 0, "Dwarf");
use batallas;
select warrior_id, warrior_name, warriors.race_id, race_name, hp, strength, speed, agility, defense, warrior_image_path from warriors inner join race on warriors.race_id = race.race_id;
select weapon_id, weapon_name, strength, speed, weapon_race, weapon_image_path from weapons;
