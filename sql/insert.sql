/* Example data*/



/* Add Authors */
INSERT INTO author (AuthorName, AuthorSurname, AuthorPhoto, AuthorBornDate, AuthorBiography)
VALUES 
	('Stephen', 'King', 'http://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/Stephen_King%2C_Comicon.jpg/471px-Stephen_King%2C_Comicon.jpg', '1947-09-21', 'Stephen Edwin King (born September 21, 1947) is an American author of contemporary horror, suspense, science fiction and fantasy. His books have sold more than 350 million copies and many of them have been adapted into feature films, television movies and comic books. King has published fifty novels, including seven under the pen name Richard Bachman, and five non-fiction books. He has written nearly two hundred short stories, most of which have been collected in nine collections of short fiction. Many of his stories are set in his home state of Maine.'),
	('Clive', 'Cussler', 'http://www.numa.net/images/cussler-2009.jpg', '1931-07-15', 'Clive Eric Cussler (born July 15, 1931) is an American adventure novelist and marine archaeologist. His thriller novels, many featuring the character Dirk Pitt, have reached The New York Times fiction best-seller list more than 20 times. Cussler is the founder and chairman of the real-life National Underwater and Marine Agency (NUMA), which has discovered more than sixty shipwreck sites and numerous other notable sunken underwater wreckages. He is the sole author or lead author of more than 50 books.'),
	('Isaac', 'Asimov', 'http://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Isaac.Asimov01.jpg/415px-Isaac.Asimov01.jpg', '1920-01-02', 'Isaac Asimov (born Isaak Yudovich Ozimov; c. January 2, 1920 – April 6, 1992) was an American author and professor of biochemistry at Boston University, best known for his works of science fiction and for his popular science books. Asimov was one of the most prolific writers of all time, having written or edited more than 500 books and an estimated 90,000 letters and postcards. His books have been published in nine out of ten major categories of the Dewey Decimal Classification.'),
	('Friedrich', 'Nietzsche', 'http://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Nietzsche187a.jpg/225px-Nietzsche187a.jpg', '1844-10-15', 'Friedrich Wilhelm Nietzsche was a German philologist, philosopher, cultural critic, poet and composer. He wrote several critical texts on religion, morality, contemporary culture, philosophy and science, displaying a fondness for metaphor, irony and aphorism.'),
	('Charles', 'Dickens', 'http://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/Dickens_Gurney_head.jpg/220px-Dickens_Gurney_head.jpg', '1812-07-02', 'Charles John Huffam Dickens (7 February 1812 – 9 June 1870) was an English writer and social critic. He created some of the world\'s most memorable fictional characters and is generally regarded as the greatest novelist of the Victorian period. During his life, his works enjoyed unprecedented fame, and by the twentieth century his literary genius was broadly acknowledged by critics and scholars. His novels and short stories continue to be widely popular'),
	('Agatha', 'Christie', 'http://upload.wikimedia.org/wikipedia/commons/c/cf/Agatha_Christie.png', '1890-09-15', 'Dame Agatha Mary Clarissa Christie, DBE (born Miller; 15 September 1890 – 12 January 1976) was an English crime novelist, short story writer, and playwright. She also wrote six romances under the name Mary Westmacott, but she is best remembered for the 66 detective novels and 14 short story collections she wrote under her own name, most of which revolve around the investigations of such characters as Hercule Poirot, Miss Jane Marple and Tommy and Tuppence.'),
	('George', 'Orwell', 'http://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/George_Orwell_press_photo.jpg/220px-George_Orwell_press_photo.jpg', '1903-06-25', 'Eric Arthur Blair (25 June 1903 – 21 January 1950), known by his pen name George Orwell, was an English novelist, essayist, journalist and critic. His work is marked by lucid prose, awareness of social injustice, opposition to totalitarianism, and commitment to democratic socialism.');



/* Add Books */
INSERT INTO Book (BookTitle, BookCover, BookGenre, BookIsbnCode, BookDescription)
VALUES
	('A Christmas Carol', 'http://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Charles_Dickens-A_Christmas_Carol-Title_page-First_edition_1843.jpg/220px-Charles_Dickens-A_Christmas_Carol-Title_page-First_edition_1843.jpg', 'Novella', '9781405409803', 'A Christmas Carol is a novella by English author Charles Dickens, first published by Chapman & Hall on 19 December 1843. It tells the story of bitter old miser Ebenezer Scrooge and his transformation resulting from supernatural visits by Jacob Marley and the Ghosts of Christmases Past, Present and Yet to Come. The novella met with instant success and critical acclaim.'),
	('Under the Dome', 'http://upload.wikimedia.org/wikipedia/en/thumb/0/09/Under_the_Dome_Final.jpg/200px-Under_the_Dome_Final.jpg', 'Science fiction', '9788994210698', 'Under the Dome is a science fiction novel by Stephen King published in November 2009. Set in and around a small Maine town, it tells an intricate, multi-character and point-of-view story of how the town\'s inhabitants contend with the calamity of being suddenly cut off from the outside world by an impassable, invisible barrier; one that literally drops out of a clear blue sky.'),
	('The Shining', 'http://upload.wikimedia.org/wikipedia/en/thumb/4/4c/Shiningnovel.jpg/200px-Shiningnovel.jpg', 'Gothic novel', '9780452267220', 'The Shining is a horror novel by American author Stephen King. Published in 1977, it is King\'s third published novel and first hardback bestseller, and the success of the book firmly established King as a preeminent author in the horror genre. The setting and characters are influenced by King\'s personal experiences, including his visit to The Stanley Hotel in 1974 and of his recovery from alcoholism.'),
	('Desperation', 'http://upload.wikimedia.org/wikipedia/en/thumb/6/6f/Desperationbook.jpg/200px-Desperationbook.jpg', 'Horror', '9780965026390', 'Desperation is a horror novel by Stephen King. It was published in 1996 at the same time as its "mirror" novel, The Regulators. It was made into a TV film starring Ron Perlman, Tom Skeritt and Steven Weber in 2006. The two novels represent parallel universes relative to one another, and most of the characters present in one novel\'s world also exist in the other novel\'s reality, albeit in different circumstances.'),
	('I, Robot', 'http://upload.wikimedia.org/wikipedia/en/d/d5/I_robot.jpg', ' Science fiction short stories', '9780007532278', 'I, Robot is a collection of nine science fiction short stories by Isaac Asimov, first published by Gnome Press in 1950 in an edition of 5,000 copies. The stories originally appeared in the American magazines Super Science Stories and Astounding Science Fiction between 1940 and 1950. The stories are woven together as Dr. Susan Calvin tells them to a reporter (the narrator) in the 21st century. Though the stories can be read separately, they share a theme of the interaction of humans, robots, and morality, and when combined they tell a larger story of Asimov\'s fictional history of robotics.'),
	('The Mysterious Affair at Styles', 'http://upload.wikimedia.org/wikipedia/en/8/8c/Mysterious_affair_at_styles.jpg', 'Crime novel', '9781594121142', 'The story opens in England during World War I at Styles Court, an Essex country manor. Upon her husband\'s death, the wealthy widow, Emily Cavendish, inherited a life estate in Styles as well as the outright inheritance of the larger part of the late Mr. Cavendish\'s income. Mrs. Cavendish became Mrs. Inglethorp upon her recent marriage to a younger man, Alfred Inglethorp. Emily\'s two stepsons, John and Lawrence Cavendish, John\'s wife Mary and Cynthia Murdoch, also live at Styles.'),
	('The Secret Adversary', 'http://upload.wikimedia.org/wikipedia/en/0/03/Secret_Adversary_First_Edition_Cover_1922.jpg', 'Crime novel', '9780553137774', 'In the Prologue, a man aboard the RMS Lusitania on 7 May 1915 quietly gives important papers to a young American woman, as she is more likely to survive the sinking ship. In 1919 London, demobilised soldier Tommy Beresford meets war volunteer Prudence "Tuppence" Cowley, both out of work and money. They form the "The Young Adventurers, Ltd", planning to hire themselves out with "[n]o unreasonable offer refused." They are overheard by a Mr Whittington who follows Tuppence to offer her a position. He is shocked when she gives her name as "Jane Finn". Whittington sends her away with some money, then disappears without a trace. They advertise for information regarding Jane Finn.'),
	('The Murder on the Links', 'http://upload.wikimedia.org/wikipedia/en/a/af/The_Murder_on_the_Links_US_First_Edition_cover_1923.jpg', 'Crime novel', '9780586045138', 'Captain Hastings has breakfast in the flat that he shares with Poirot in London. Poirot receives an extraordinary letter: "For God\'s sake, come!" writes Monsieur Paul Renauld. Directly, Poirot and Hastings go to Renauld\'s home, the Villa Genevieve in Merlinville-sur-Mer on the north coast of France. Near the villa, they are watched by a beautiful girl who has "anxious eyes". At the gates, the police tell them that Renaud has been murdered this morning.'),
	('Animal Farm', 'http://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/AnimalFarm_1stEd.jpg/200px-AnimalFarm_1stEd.jpg', 'Satire, Political literature, Educational animation', '9780141036137', 'Describes what happens when the animals drive out Mr Jones, the farmer, and attempt to run the Manor Farm themselves. This book has become a world-famous classic of English prose, and is a strong political allegory with parallels to the old Soviet regime.'),
	('Nineteen Eighty-Four', 'http://upload.wikimedia.org/wikipedia/en/thumb/c/c3/1984first.jpg/200px-1984first.jpg', 'Dystopian, political fiction, social science fiction', '9780143566496', 'When an essay is due and dreaded exams loom, this book offers students what they need to succeed. It provides chapter-by-chapter analysis, explanations of key themes, motifs and symbols, a review quiz, and essay topics. It is suitable for late-night studying and paper writing.'),
	('Homage to Catalonia', 'http://upload.wikimedia.org/wikipedia/en/thumb/b/b6/Homage_to_Catalonia%2C_Cover%2C_1st_Edition.jpg/220px-Homage_to_Catalonia%2C_Cover%2C_1st_Edition.jpg', 'Non-Fiction, Political', '9780436350054', 'In 1936 Orwell went to Spain to report on the Civil War and instead joined the fight against the Fascists. This famous account describes the war and Orwell\'s experiences.');


/* Add Writes */
INSERT INTO Writes (BookID, AuthorID)
VALUES
	(1, 5),
	(2, 1),
	(3, 1),
	(4, 1),
	(5, 3),
	(6, 6),
	(7, 6),
	(8, 6),
	(9, 7),
	(10, 7),
	(11, 7);



/* Add Customers */
INSERT INTO Customer (CustomerEmail, CustomerName, CustomerSurname)
VALUES
	('davide@pastore.it', 'Davide', 'Pastore'),
	('pietro@santoro.it', 'Pietro', 'Santoro'),
	('cosimo@giannuzzi.it', 'Cosimo', 'Giannuzzi'),
	('federico@mastrangelo.it', 'Federico', 'Mastrangelo'),
	('gianni@luconi.it', 'Gianni', 'Luconi'),
	('giuseppe@polimeno.it', 'Giuseppe', 'Polimeno');



/* Add Users */
INSERT INTO User (UserEmail, UserPassword, UserName, UserSurname, UserBornDate)
VALUES
	('test@test.it', 'test', 'Test', 'Test', '1980-02-15');



/* Add Copies */
INSERT INTO Copy (BookID)
VALUES
	(1),
	(1),
	(1),
	(2),
	(2),
	(3),
	(5),
	(5),
	(6),
	(7),
	(8),
	(9),
	(9),
	(9),
	(9),
	(9),
	(10),
	(10),
	(10),
	(10),
	(10);



/* Add Loans */
INSERT INTO Loan (CustomerCardNumber, CopyID, LoanStartDate, LoanEndDate, LoanReturned)
VALUES
	(1, 1, '2014-02-10', '2014-02-14', FALSE),
	(2, 2, '2014-02-14', '2014-02-25', FALSE),
	(3, 4, '2014-02-19', '2014-02-21', FALSE),
	(4, 7, '2014-01-19', '2014-02-21', TRUE);

