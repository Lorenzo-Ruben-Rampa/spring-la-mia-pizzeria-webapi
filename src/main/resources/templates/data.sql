-- src/main/resources/data.sql
-- PIZZAS
INSERT INTO pizzas (id, name, description, url_image, price) VALUES
(1, 'Margherita', 'La classica pizza napoletana che celebra i colori italiani: la semplicità della mozzarella che si fonde con il rosso del pomodoro e il verde del basilico fresco.', '/img/margherita.jpg', 6.50),
(2, 'Marinara', 'Una pizza dall''anima mediterranea, dove l''aglio e l''origano danzano nell''olio d''oliva su un letto di pomodoro, senza mozzarella per un gusto deciso e autentico.', '/img/marinara.jpg', 5.50),
(3, 'Diavola', 'Per chi ama il fuoco in bocca! Il salame piccante avvolto dalla mozzarella crea un contrasto audace con la dolcezza del pomodoro.', '/img/diavola.jpg', 7.50),
(4, 'Quattro Formaggi', 'Un concerto di sapori cremosi e intensi: mozzarella, gorgonzola, parmigiano e pecorino si fondono in un''armonia da gourmet.', '/img/quattro-formaggi.jpg', 8.00),
(5, 'Capricciosa', 'La pizza che non rinuncia a nulla! Prosciutto, funghi, carciofi e olive si incontrano in un trionfo di sapori che soddisferà ogni capriccio.', '/img/capricciosa.jpg', 8.50),
(6, 'Prosciutto e Funghi', 'Il comfort food per eccellenza: il matrimonio perfetto tra la dolcezza del prosciutto cotto e la terra dei funghi, avvolti da una coperta di mozzarella.', '/img/prosciutto-funghi.jpg', 7.50),
(7, 'Quattro Stagioni', 'Un viaggio gastronomico attraverso le stagioni: ogni spicchio è una sorpresa diversa, dall''estate del prosciutto all''autunno dei funghi.', '/img/quattro-stagioni.jpg', 8.50),
(8, 'Bianca', 'Elegante e senza pomodoro: la cremosità della ricotta incontra la mozzarella in una danza leggera aromatizzata da rosmarino e olio d''oliva.', '/img/bianca.jpg', 7.00),
(9, 'Tonno e Cipolla', 'Un abbraccio tra mare e terra: la delicatezza del tonno si sposa con la croccantezza della cipolla in un equilibrio sorprendente.', '/img/tonno-cipolla.jpg', 7.50),
(10, 'Vegetariana', 'Un giardino in pizza! Melanzane, zucchine e peperoni grigliati portano in tavola tutta la freschezza dell''orto con un tocco di mozzarella.', '/img/vegetariana.jpg', 8.00);


-- src/main/resources/data.sql
-- INGREDIENTS
-- Inserimento degli ingredienti con la colonna url_image (percorsi assoluti) 
INSERT INTO ingredients (name, url_image) VALUES
('Pomodoro', '/img/pomodoro.jpg'),
('Mozzarella', '/img/mozzarella.jpg'),
('Basilico', '/img/basilico.jpg'),
('Aglio', '/img/aglio.jpg'),
('Origano', '/img/origano.jpg'),
('Salame Piccante', '/img/salame-piccante.jpg'),
('Gorgonzola', '/img/gorgonzola.jpg'),
('Parmigiano', '/img/parmigiano.jpg'),
('Pecorino', '/img/pecorino.jpg'),
('Prosciutto Cotto', '/img/prosciutto-cotto.jpg'),
('Funghi', '/img/funghi.jpg'),
('Carciofi', '/img/carciofi.jpg'),
('Olive Nere', '/img/olive-nere.jpg'),
('Ricotta', '/img/ricotta.jpg'),
('Rosmarino', '/img/rosmarino.jpg'),
('Tonno', '/img/tonno.jpg'),
('Cipolla', '/img/cipolla.jpg'),
('Melanzane', '/img/melanzane.jpg'),
('Zucchine', '/img/zucchine.jpg'),
('Peperoni', '/img/peperoni.jpg');
