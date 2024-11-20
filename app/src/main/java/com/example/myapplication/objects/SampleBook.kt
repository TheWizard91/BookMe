package com.example.myapplication.objects

import com.example.myapplication.R
import com.example.myapplication.classes.Book
import kotlin.random.Random

object SampleBook {

    val books = listOf<Book>(
        Book(
            id = 1,
            title ="To Kill a Mockingbird",
            author = "Harper Lee",
            publicationYear = 1960,
            genres = listOf("Fiction", "Classic"),
            description = "A classic novel depicting racial injustice in the American South.",
            longDescription = "The unforgettable novel of a childhood in a sleepy Southern town and the crisis of conscience that rocked it, To Kill A Mockingbird became both an instant bestseller and a critical success when it was first published in 1960. " +
                    "It went on to win the Pulitzer Prize in 1961 and was later made into an Academy Award-winning film, also a classic.\n" +
                    "\n" +
                    "Compassionate, dramatic, and deeply moving, To Kill A Mockingbird takes readers to the roots of human behavior - to innocence and experience, kindness and cruelty, love and hatred, humor and pathos. " +
                    "Now with over 18 million copies in print and translated into forty languages, this regional story by a young Alabama woman claims universal appeal. " +
                    "Harper Lee always considered her book to be a simple love story. Today it is regarded as a masterpiece of American literature.",
            coverImage = "https://i.pinimg.com/564x/b3/b6/5b/b3b65b82c7820c5cbc15a5fef871d68d.jpg",
            localCoverImagePath = R.drawable.to_kill_a_mockingbird,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 2,
            title = "1984",
            author = "George Orwell",
            publicationYear = 1949,
            genres = listOf("Dystopian","Science Fiction"),
            description = "A dystopian novel portraying a totalitarian society.",
            longDescription = "Written 75 years ago, 1984 was George Orwell’s chilling prophecy about the future. " +
                    "And while 1984 has come and gone, his dystopian vision of a government that will do anything to control the narrative is timelier than ever...\n" +
                    "\n" +
                    "This 75th Anniversary Edition includes:\n" +
                    "• A New Introduction by Dolen Perkins-Valdez, author of Take My Hand, winner of the 2023 NAACP Image Award for Outstanding Literary Work—Fiction\n" +
                    "• A New Afterword by Sandra Newman, author of Julia: A Retelling of George Orwell’s 1984\n" +
                    "\n" +
                    "“The Party told you to reject the evidence of your eyes and ears. It was their final, most essential command.”\n" +
                    "\n" +
                    "Winston Smith toes the Party line, rewriting history to satisfy the demands of the Ministry of Truth. With each lie he writes, Winston grows to hate the Party that seeks power for its own sake and persecutes those who dare to commit thoughtcrimes. But as he starts to think for himself, Winston can’t escape the fact that Big Brother is always watching...\n" +
                    "\n" +
                    "A startling and haunting novel, 1984 creates an imaginary world that is completely convincing from start to finish." +
                    " No one can deny the novel’s hold on the imaginations of whole generations, or the power of its admonitions—a power that seems to grow, not lessen, with the passage of time.",
            coverImage = "https://i.pinimg.com/564x/04/44/6c/04446c92480b149be2e02f7a4f520922.jpg",
            localCoverImagePath = R.drawable.nineteeneightyfour,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 3,
            title = "Pride and Prejudice",
            author = "Jane Austen",
            publicationYear = 1813,
            genres = listOf("Classic","Romance"),
            description = "A classic novel exploring themes of love, marriage, and social norms.",
            longDescription = "Austen's most popular novel, the unforgettable story of Elizabeth Bennet and Mr. Darcy\n" +
                    "\n" +
                    "Few have failed to be charmed by the witty and independent spirit of Elizabeth Bennet in Austen’s beloved classic Pride and Prejudice. " +
                    "When Elizabeth Bennet first meets eligible bachelor Fitzwilliam Darcy, she thinks him arrogant and conceited; he is indifferent to her good looks and lively mind. " +
                    "When she later discovers that Darcy has involved himself in the troubled relationship between his friend Bingley and her beloved sister Jane, she is determined to dislike him more than ever. " +
                    "In the sparkling comedy of manners that follows, Jane Austen shows us the folly of judging by first impressions and superbly evokes the friendships, gossip and snobberies of provincial middle-class life. " +
                    "This Penguin Classics edition, based on Austen's first edition, contains the original Penguin Classics introduction by Tony Tanner and an updated introduction and notes by Viven Jones.\n" +
                    "\n" +
                    "For more than seventy years, Penguin has been the leading publisher of classic literature in the English-speaking world. " +
                    "With more than 1,700 titles, Penguin Classics represents a global bookshelf of the best works throughout history and across.",
            coverImage = "https://i.pinimg.com/564x/5c/12/6e/5c126ebc616400330587845a172c110d.jpg",
            localCoverImagePath = R.drawable.pride_and_prejudice,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 4,
            title = "The Great Gatsby",
            author = "F. Scott Fitzgerald",
            publicationYear = 1925,
            genres = listOf("Fiction", "Classic"),
            description = "A tale of the American Dream, wealth, and love during the Roaring Twenties.",
            longDescription = "Enjoy an affordable edition of one of the 20th century's greatest novels." +
                    " Read about Jay Gatsby and his journey to realize the ultimate American Dream, while exploring timeless themes such as honesty, temptation, and escaping the past.\n" +
                    " \n" +
                    "\"One of the most important works in American literature — and, to many, the great American novel.\" — Time.\n" +
                    "\n" +
                    "\"I want to write something new,\" F. Scott Fitzgerald told his editor, Maxwell Perkins, \"something extraordinary and beautiful and simple and intricately patterned.\" A century later, Fitzgerald's little book of big ideas retains its freshness and excitement. " +
                    "The quintessential portrait of Jazz Age America, it reflects the era's postwar exuberance as well as the corruption and immorality lurking behind the glamour of wild parties, dancing, and illegal drinking.\n" +
                    "Narrator Nick Carraway — a transplant from the Midwest like Fitzgerald himself — observes the wasteful lives of his well-to-do neighbors in this tale of money, love, and the pursuit of the American dream. " +
                    "The unforgettable cast is headed by Jay Gatsby, a self-made man whose determination to realize his fantasies embodies both the glories of imagination and the grimness of reality. " +
                    "Above all, The Great Gatsby is animated by the magic of Fitzgerald's incandescent prose and its timeless exploration of the importance of honesty, the temptations of wealth, and the struggle to escape the past.\n" +
                    "\n" +
                    "The Great Gatsby is a timeless exploration of the American Dream, the importance of honesty, the temptations of wealth, and the struggle to escape the past - all wrapped in F. Scott Fitzgerald's incandescent prose.\n" +
                    "Immerse yourself in the exuberance and glamour of wild parties, dancing, and illegal drinking with narrator Nick Carraway and unforgettable characters Jay Gatsby and Daisy Buchanan. Gain perspective on F. Scott Fitzgerald’s observations of the postwar exuberance, corruption and immorality.\n" +
                    "The Thrift Edition of The Great Gatsby by F. Scott Fitzgerald is an economical choice for budget-conscious readers. Enjoy this classic story for a fraction of the price. It’s also easy to carry around, making it perfect for readers on the go.\n" +
                    "Share F. Scott Fitzgerald's beloved masterpiece with your friends. This timeless novel continues to be regarded as one of literature's greatest achievements. Grab a copy today and explore what makes The Great Gatsby so special!",
            coverImage = "https://i.pinimg.com/736x/11/91/8f/11918f3c356159ed09856eb1664f9bff.jpg",
            localCoverImagePath = R.drawable.the_great_gatsby,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 5,
            title = "Moby-Dick",
            author = "Herman Melville",
            publicationYear = 1851,
            genres = listOf("Fiction", "Adventure"),
            description = "The epic tale of Captain Ahab's obsession with the white whale.",
            longDescription = "A masterpiece of storytelling, this epic saga pits Ahab, a brooding and fantastical sea captain, against the great white whale that crippled him. " +
                    "In telling the tale of Ahab's passion for revenge and the fateful voyage that ensued, Melville produced far more than the narrative of a hair-raising journey; Moby-Dick is a tale for the ages that sounds the deepest depths of the human soul.\n" +
                    "Interspersed with graphic sketches of life aboard a whaling vessel, and a wealth of information on whales and 19th-century whaling, Melville's greatest work presents an imaginative and thrilling picture of life at sea, as well as a portrait of heroic determination. " +
                    "The author's keen powers of observation and firsthand knowledge of shipboard life (he served aboard a whaler himself) were key ingredients in crafting a maritime story that dramatically examines the conflict between man and nature.\n" +
                    "\"A valuable addition to the literature of the day,\" said American journalist Horace Greeley on the publication of Moby-Dick in 1851 — a classic piece of understatement about a literary classic now considered by many as \"the great American novel.\" " +
                    "Read and pondered by generations, the novel remains an unsurpassed account of the ultimate human struggle against the indifference of nature and the awful power of fate.\n" +
                    "Much of Moby Dick was inspired by the 1821 work Narratives of the Wreck of the Whale-Ship Essex, which in turn inspired the 2015 movie In the Heart of the Sea, directed by Ron Howard and starring Chris Hemsworth.",
            coverImage = "https://i.pinimg.com/564x/7a/49/68/7a49682a4ddac9cefb030be8db35a6af.jpg",
            localCoverImagePath = R.drawable.moby_dick,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 6,
            title = "The Lord of the Rings",
            author = "J.R.R. Tolkien",
            publicationYear = 1954,
            genres = listOf("Fantasy","Adventure"),
            description = "An epic fantasy saga about the quest to destroy the One Ring.",
            longDescription = "For the first time ever, a very special edition of the J. R. R. " +
                    "Tolkien's classic masterpiece, The Lord of the Rings, gorgeously illustrated throughout in color by the author himself and with the complete text printed in two colors, plus sprayed edges and a ribbon bookmark.\n" +
                    "\n" +
                    "Since it was first published in 1954, The Lord of the Rings has been a book people have treasured. " +
                    "Steeped in unrivaled magic and otherworldliness, Tolkien's sweeping fantasy and epic adventure has touched the hearts of young and old alike. " +
                    "More than 150 million copies of its many editions have been sold around the world, and occasional collectors’ editions become prized and valuable items of publishing.\n" +
                    "\n" +
                    "This one-volume, jacketed hardcover edition contains the complete text, fully corrected and reset, which is printed in red and black and features, for the very first time, thirty color illustrations, maps and sketches drawn by Tolkien himself as he composed this epic work. " +
                    "These include the pages from the Book of Mazarbul, marvelous facsimiles created by Tolkien to accompany the famous ‘Bridge of Khazad-dum’ chapter. " +
                    "Also appearing are two removable fold-out maps drawn by Christopher Tolkien revealing all the detail of Middle-earth.\n" +
                    "\n" +
                    "Sympathetically packaged to reflect the classic look of the first edition, this new edition of the bestselling hardback will prove irresistible to collectors and new fans alike.",
            coverImage = "https://i.pinimg.com/564x/7b/6d/38/7b6d3896048f20a865b744ab69aaf216.jpg",
            localCoverImagePath = R.drawable.the_lord_of_the_rings,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 7,
            title = "The Catcher in the Rye",
            author = "J.D. Salinger",
            publicationYear = 1951,
            genres = listOf("Fiction","Coming-of-age"),
            description = "A classic coming-of-age novel following Holden Caulfield's journey.",
            longDescription = "Anyone who has read J.D. Salinger's New Yorker stories--particularly A Perfect Day for Bananafish, Uncle Wiggily in Connecticut, The Laughing Man, and For Esme With Love and Squalor--will not be surprised by the fact that his first novel is full of children. " +
                    "The hero-narrator of The Catcher in the Rye is an ancient child of sixteen, a native New Yorker named Holden Caulfield.\n" +
                    "\n" +
                    "Through circumstances that tend to preclude adult, secondhand description, he leaves his prep school in Pennsylvania and goes underground in New York City for three days. " +
                    "The boy himself is at once too simple and too complex for us to make any final comment about him or his story. " +
                    "Perhaps the safest thing we can say about Holden is that he was born in the world not just strongly attracted to beauty but, almost, hopelessly impaled on it.\n" +
                    "\n" +
                    "There are many voices in this novel: children's voices, adult voices, underground voices-but Holden's voice is the most eloquent of all. " +
                    "Transcending his own vernacular, yet remaining marvelously faithful to it, he issues a perfectly articulated cry of mixed pain and pleasure. " +
                    "However, like most lovers and clowns and poets of the higher orders, he keeps most of the pain to, and for, himself. " +
                    "The pleasure he gives away, or sets aside, with all his heart. " +
                    "It is there for the reader who can handle it to keep.",
            coverImage = "https://i.pinimg.com/564x/4b/be/9c/4bbe9c695bd2c2005847108120df0612.jpg",
            localCoverImagePath = R.drawable.the_catcher_in_the_ray,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 8,
            title = "The Hobbit",
            author = "J.R.R. Tolkien",
            publicationYear = 1937,
            genres = listOf("Fantasy", "Adventure"),
            description = "The prequel to The Lord of the Rings, following Bilbo Baggins' journey.",
            longDescription = "The journey through Middle-earth begins here with J.R.R. Tolkien's classic prelude to his Lord of the Rings trilogy.\n" +
                    "\n" +
                    "“A glorious account of a magnificent adventure, filled with suspense and seasoned with a quiet humor that is irresistible... " +
                    "All those, young or old, who love a fine adventurous tale, beautifully told, will take The Hobbit to their hearts.”—The New York Times Book Review\n" +
                    "\n" +
                    "\"In a hole in the ground there lived a hobbit.\" " +
                    "So begins one of the most beloved and delightful tales in the English language—Tolkien's prelude to The Lord of the Rings. " +
                    "Set in the imaginary world of Middle-earth, at once a classic myth and a modern fairy tale, The Hobbit is one of literature's most enduring and well-loved novels.\n" +
                    "\n" +
                    "Bilbo Baggins is a hobbit who enjoys a comfortable, unambitious life, rarely traveling any farther than his pantry or cellar. " +
                    "But his contentment is disturbed when the wizard Gandalf and a company of dwarves arrive on his doorstep one day to whisk him away on an adventure. " +
                    "They have launched a plot to raid the treasure hoard guarded by Smaug the Magnificent, a large and very dangerous dragon. " +
                    "Bilbo reluctantly joins their quest, unaware that on his journey to the Lonely Mountain he will encounter both a magic ring and a frightening creature known as Gollum.",
            coverImage = "https://i.pinimg.com/564x/fb/b0/74/fbb074b6a0674cb137b39d2b2acaf467.jpg",
            localCoverImagePath = R.drawable.the_hobit,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 9,
            title = "One Hundred Years of Solitude",
            author = "Gabriel Garcia Marquez",
            publicationYear = 1967,
            genres = listOf("Magical Realism", "Literary Fiction"),
            description = "A multi-generational saga of the Buendía family in the fictional town of Macondo.",
            longDescription = "Includes a bonus PDF with a character chart!\n" +
                    "\n" +
                    "One of the twentieth century’s enduring works, One Hundred Years of Solitude is a widely beloved and acclaimed novel known throughout the world and the ultimate achievement in a Nobel Prize–winning career.\n" +
                    "\n" +
                    "The novel tells the story of the rise and fall of the mythical town of Macondo through the history of the Buendía family. " +
                    "Rich and brilliant, it is a chronicle of life, death, and the tragicomedy of humankind. " +
                    "In the beautiful, ridiculous, and tawdry story of the Buendía family, one sees all of humanity, just as in the history, myths, growth, and decay of Macondo, one sees all of Latin America.\n" +
                    "\n" +
                    "Love and lust, war and revolution, riches and poverty, youth and senility, the variety of life, the endlessness of death, the search for peace and truth—these universal themes dominate the novel. " +
                    "Alternately reverential and comical, One Hundred Years of Solitude weaves the political, personal, and spiritual to bring a new consciousness to storytelling. " +
                    "Translated into dozens of languages, this stunning work is no less than an account of the history of the human race.\n" +
                    "\n" +
                    "Read less\n" +
                    "©1964 Gabriel García Márquez (P)2013 Blackstone Audio",
            coverImage = "https://i.pinimg.com/564x/fb/6c/57/fb6c57be3005b5759370732f9831ecb0.jpg",
            localCoverImagePath = R.drawable.one_hundred_years_of_solitude,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 10,
            title = "War and Peace",
            author = "Leo Tolstoy",
            publicationYear = 1869,
            genres = listOf("Historical Fiction", "Epic"),
            description = "A monumental work depicting the events of Russian society during the Napoleonic era.",
            longDescription = "In early nineteenth-century Russia, the threat of Napoleon’s invasion looms, and the lives of millions are about to be changed forever. " +
                    "This includes Pierre Bezúkhov, illegitimate son of an aristocrat; Andrew Bolkónski, ambitious military scion; and Natásha Rostóva, compassionate daughter of a nobleman. " +
                    "All of them are unprepared for what lies ahead. Alongside their fellow compatriots - a catalog of enduring literary characters - Pierre, Andrew, and Natásha will be irrevocably torn between fate and free will.\n" +
                    "\n" +
                    "Through the bonds of love and family, and all that can break them, Tolstoy examines the effects of war on every strata of society in his masterwork of intimate - and epic - social history.\n" +
                    "\n" +
                    "Revised edition: Previously published as War and Peace, this edition of War and Peace (AmazonClassics Edition) includes editorial revisions.",
            coverImage = "https://i.pinimg.com/736x/70/15/41/70154164a2967cc25fbac089e855db38.jpg",
            localCoverImagePath = R.drawable.war_and_peace,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 11,
            title = "The Odyssey",
            author = "Homer",
            publicationYear = 700,
            genres = listOf("Epic","Mythology"),
            description = "An ancient Greek epic poem recounting Odysseus' ten-year journey home after the Trojan War.",
            longDescription = "The Odyssey is literature's grandest evocation of every man's journey through life. " +
                    "In the myths and legends that are retold here, the energy and poetry of Homer's original is captured in a bold, contemporary idiom, giving us an edition of The Odyssey that is a joy to listen to, worth savoring and treasuring for its sheer lyrical mastery.\n" +
                    "\n" +
                    "This audiobook is sure to delight both the classicist and the general listener and to captivate a new generation of Homer's students.",
            coverImage = "https://i.pinimg.com/564x/38/1a/4a/381a4abdc63125efd1e31f0cf27571a0.jpg",
            localCoverImagePath = R.drawable.the_oddysey,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 12,
            title = "The Divine Comedy",
            author = "Dante Alighieri",
            publicationYear = 1320,
            genres = listOf("Epic", "Poetry"),
            description = "An epic poem that follows the journey of the soul through Hell, Purgatory, and Heaven.",
            longDescription = "",
            coverImage = "https://i.pinimg.com/564x/6f/11/5a/6f115a218b2a30a66c252414a693529b.jpg",
            localCoverImagePath = R.drawable.the_divine_comedy,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 13,
            title = "The Brothers Karamazov",
            author = "Fyodor Dostoevsky",
            publicationYear = 1880,
            genres = listOf("Classic","Philosophical Fiction"),
            description = "A complex novel exploring themes of spirituality, morality, and human nature.",
            longDescription = "Blake Ritson, David Warner, Hattie Morahan and John Hurt star in this BBC Radio 4 dramatisation of Dante's epic poem.\n" +
                    "\n" +
                    "Inferno: Thirty-five year old Dante finds himself in the middle of a dark wood, in extreme personal and spiritual crisis. " +
                    "Hope of rescue appears in the form of the venerable poet Virgil, now a shade himself, who offers to lead Dante on an odyssey through the afterlife, beginning in the terrifying depths of Hell.\n" +
                    "\n" +
                    "Purgatorio: Dante is led up Mount Purgatory by his guide. " +
                    "They encounter numerous souls who have embarked on the same difficult journey - one that will eventually lead to their spiritual salvation.\n" +
                    "\n" +
                    "Paradiso: Dante's journey comes to a glorious conclusion as he is led by Beatrice, through the spheres of Paradise and into the presence of God himself. " +
                    "As they ascend, they encounter a number of souls who have also achieved blessedness. " +
                    "Many years later, the older Dante reflects on the episodes from his life that have inspired his great poem.",
            coverImage = "https://i.pinimg.com/564x/d5/69/3b/d5693b4b5cdaf0c97e706c098ebe828e.jpg",
            localCoverImagePath = R.drawable.the_brothers_karamazov,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 14,
            title = "Crime and Punishment",
            author = "Fyodor Dostoevsky",
            publicationYear = 1866,
            genres = listOf("Classic", "Psychological Fiction"),
            description = "A psychological thriller revolving around guilt, conscience, and redemption.",
            longDescription = "Nominated as one of America’s best-loved novels by PBS’s The Great American Read.\n" +
                    "\n" +
                    "With the same suppleness, energy, and range of voices that won their translation of The Brothers Karamazov the PEN/Book-of-the-Month Club Prize, Pevear and Volokhonsky offer a brilliant translation of Dostoevsky's classic novel that presents a clear insight into this astounding psychological thriller.\n" +
                    "\n" +
                    "\"The best (translation) currently available.\" (Washington Post Book World)\n" +
                    "\n" +
                    "This audio edition of Crime and Punishment is expressively brought to life by Peter Batchelor.\n" +
                    "\n" +
                    "©1992 Translation Copyright by Richard Pevear and Larissa Volokhonsky (P)2020 Echo Point Books & Media, LLC",
            coverImage = "https://i.pinimg.com/564x/7b/5f/06/7b5f06ac3e549659269ff9fc478444dc.jpg",
            localCoverImagePath = R.drawable.crime_and_punishment,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 15,
            title = "The Picture of Dorian Gray",
            author = "Oscar Wilde",
            publicationYear = 1890,
            genres = listOf("Gothic", "Philosophical Fiction"),
            description = "A novel about a man whose portrait ages while he retains his youth and beauty.",
            longDescription = "Exclusively from Audible\n" +
                    "\n" +
                    "The Picture of Dorian Gray was first published in 1890 in the July edition of the Lippincott Magazine. Now, this special anniversary edition marks 50 years since the 1967 Sexual Offences Act was passed in England. " +
                    "25p from every copy downloaded over the next 12 months will be donated to Stonewall, Britain’s leading LGBT charity, to help the organisation further its work in securing full equality for lesbian, gay, bi and trans people everywhere.\n" +
                    "\n" +
                    "A damning portrayal of Victorian society, Wilde used his narrative to chastise his contemporaries for their superfluous and hypocritical values. " +
                    "Having also interspersed homoerotic scenes within the story, The Picture of Dorian Gray was unsurprisingly condemned for its 'indecency', forcing Wilde to publish a second, censored edition in 1891. " +
                    "Wilde defended his vision to the last, whilst simultaneously challenging assumptions about his private life and sexuality. " +
                    "He credited his inspiration for the text to the classic Faustian suggestion that given the chance, a man would undoubtedly sell his soul in exchange for eternal youth.\n" +
                    "\n" +
                    "When the protagonist, Dorian Gray, meets with the audacious Lord Henry Wotton, he is encouraged to indulge in his most vain and hedonistic of ambitions, thereby testing the boundaries of the law and living a life of unpunished anarchy. " +
                    "As handsome as he is charming, Dorian beguiles those around him, in particular, the artist Basil Hallward. Hopelessly enamoured by the young socialite, Basil sets out to capture his likeness in a full-length portrait. " +
                    "It is the finished product which ultimately engenders Dorian the ultimate weapon; control over the passing of time.\n" +
                    "\n" +
                    "Modern audiences now recognise The Picture of Dorian Gray as an enticing gothic masterpiece and highly astute cautionary tale. " +
                    "Experience the unique and fractured world created by Oscar Wilde in this new audiobook adaptation, made in collaboration with Stonewall and narrated by award-winning actor, Russell Tovey.\n" +
                    "\n" +
                    "Narrator Biography\n" +
                    "\n" +
                    "Russell Tovey is a TV, film and stage actor, known for The History Boys, Grabbers, Angels in America, The Night Manager, Pride, Quantico and The Pass. " +
                    "He has narrated many audiobooks throughout his career including, Nick Hornby's High Fidelity, Anna Sewell's Black Beauty and Mark Michalowski's Being Human.\n" +
                    "\n" +
                    "In collaboration with Stonewall, who are celebrating the 50th anniversary of the decriminalisation of homosexuality in the UK, Tovey brings us this new adaption of Oscar Wilde's The Picture of Dorian Gray. " +
                    "Having starred in The Old Vic's production of Queers and The National Theatre's Angels in America, Russell is a keen advocate of LGBT rights and, bringing years of stage training experience, the perfect narrator for this epic tale of masculine beauty.",
            coverImage = "https://i.pinimg.com/564x/7a/6e/fe/7a6efe7338bc9f61c790d0a7b673464b.jpg",
            localCoverImagePath = R.drawable.the_picture_of_dorian_gray,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 16,
            title = "Brave New World",
            author = "Aldous Huxley",
            publicationYear = 1932,
            genres = listOf("Dystopian","Science Fiction"),
            description = "A dystopian vision of a future society obsessed with pleasure and conformity.",
            longDescription = "Brave New World is a novel written in 1931 by Aldous Huxley and published in 1932. " +
                    "Set in London in the year AD 2540 (632 A.F.—\"After Ford\"—in the book), the novel anticipates developments in reproductive technology, sleep-learning, psychological manipulation, and classical conditioning that combine profoundly to change society. " +
                    "Huxley answered this book with a reassessment in an essay, Brave New World Revisited (1958), and with Island (1962), his final novel. " +
                    "In 1999, the Modern Library ranked Brave New World fifth on its list of the 100 best English-language novels of the 20th century. " +
                    "In 2003, Robert McCrum writing for The Observer included Brave New World chronologically at number 53 in \"the top 100 greatest novels of all time\", and the novel was listed at number 87 on the BBC's survey The Big Read. " +
                    "What if the future was a tyranny, but one cleverly person intended to keep the mass of society unaware of this? " +
                    "The people would be provided with several distractions, daily life would be ruled by sex and drugs, and pervasive mass media would suppress the possibility of any original thought: in such a society the ruling elite would not need to fear any kind of rebellion. " +
                    "If you think that Huxley's vision seems to be the way things are in fact turning out, you're not the only one!",
            coverImage = "https://i.pinimg.com/564x/5f/06/65/5f066515b64613faed22947a8029f139.jpg",
            localCoverImagePath = R.drawable.brave_new_world,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 17,
            title = "The Count of Monte Cristo",
            author = "Alexandre Dumas",
            publicationYear = 1844,
            genres = listOf("Adventure", "Historical Fiction"),
            description = "An adventure novel of revenge, love, and redemption set in the early 19th century.",
            longDescription = "On the eve of his marriage to the beautiful Mercedes, having that very day been made captain of his ship, the young sailor Edmond Dantès is arrested on a charge of treason, trumped up by jealous rivals. " +
                    "Incarcerated for many lonely years in the isolated and terrifying Chateau d'If near Marseille, he meticulously plans his brilliant escape and extraordinary revenge.\n" +
                    "\n" +
                    "Of all the \"masked avengers\" and \"caped crusaders\" in literature, The Count of Monte Cristo is at once the most daring and the most vulnerable. " +
                    "Alexandre Dumas (père), master storyteller, takes us on a journey of adventure, romance, intrigue, and ultimately, redemption.\n" +
                    "\n" +
                    "Public Domain (P)2010 Naxos Audiobooks",
            coverImage = "https://i.pinimg.com/564x/76/f7/41/76f7410361344a0e15d56d8548530394.jpg",
            localCoverImagePath = R.drawable.the_count_of_monte_cristo,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 18,
            title = "Anna Karenina",
            author = "Leo Tolstoy",
            publicationYear = 1877,
            genres = listOf("Classic", "Romance"),
            description = "A tragic love story set against the backdrop of Russian high society.",
            longDescription = "Winner: Audible's Best of 2016 - Classic\n" +
                    "\n" +
                    "\"Anna Karenina is one of my favorite books. " +
                    "But when I agreed to read it for Audible, I had no idea how much work it would be, how intense it would be, and how deeply I would fall in love with it. " +
                    "There were places where I thought 'if I don't give Alexey Alexandrovitch the respect that he deserves in my reading of this scene, a critical part of the book will be ruined. " +
                    "If I don't give EVERYONE the utmost respect and understanding, I'm not doing justice to this brilliantly compassionate book.' " +
                    "But at the same time, I also wanted to have a light touch in the way I played the different characters, so that the magnificence of the novel could shine through. " +
                    "I feel like performing this novel is one of the major accomplishments of my work life - it was so challenging and so deep, a real pleasure.\" (Narrator Maggie Gyllenhaal)\n" +
                    "\n" +
                    "Leo Tolstoy's classic story of doomed love is one of the most admired novels in world literature. " +
                    "Generations of readers have been enthralled by his magnificent heroine, the unhappily married Anna Karenina, and her tragic affair with dashing Count Vronsky. " +
                    "Maggie Gyllenhaal (The Dark Knight, The Honourable Woman) cites Tolstoy's epic as one of her favorite books of all time, and her love for the literature permeates her performance. " +
                    "Anna Karenina is a masterpiece not only because of the unforgettable woman at its core and the stark drama of her fate but also because it explores and illuminates the deepest questions about how to live a fulfilled life.",
            coverImage = "https://i.pinimg.com/564x/6e/88/cc/6e88ccf11ae57c70afab36f858745a7f.jpg",
            localCoverImagePath = R.drawable.anna_karenina,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 19,
            title = "The Alchemist",
            author = "Paulo Coelho",
            publicationYear = 1988,
            genres = listOf("Fiction", "Philosophical"),
            description = "A philosophical novel about a shepherd boy's journey to find his personal legend.",
            longDescription = "Paulo Coelho's enchanting novel has inspired a devoted following around the world. " +
                    "This story, dazzling in its simplicity and wisdom, is about an Andalusian shepherd boy named Santiago who travels from his homeland in Spain to the Egyptian desert in search of treasure buried in the Pyramids. " +
                    "Along the way he meets a Gypsy woman, a man who calls himself king, and an Alchemist, all of whom point Santiago in the direction of his quest. " +
                    "No one knows what the treasure is, or if Santiago will be able to surmount the obstacles along the way But what starts out as a journey to find worldly goods turns into a meditation on the treasures found within. " +
                    "Lush, evocative, and deeply humane, the story of Santiago is art eternal testament to the transforming power of our dreams and the importance of listening to our hearts.",
            coverImage = "https://i.pinimg.com/736x/98/bc/0a/98bc0a1a761bc9242f71e2e71f89dd7f.jpg",
            localCoverImagePath = R.drawable.the_alchemist,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 20,
            title = "The Adventures of Huckleberry Finn",
            author = "Mark Twain",
            publicationYear = 1884,
            genres = listOf("Adventure", "Satire"),
            description = "A satirical novel following Huck Finn's journey down the Mississippi River.",
            longDescription = "Earphones Award Winner (AudioFile Magazine)\n" +
                    "\n" +
                    "Ernest Hemingway said, “All modern American literature comes from one book by Mark Twain called Huckleberry Finn\". " +
                    "One hundred years after its author’s death, this classic remains remarkably modern and poignantly relevant. " +
                    "In this new edition, Elijah Wood reads Huck in a youthful voice that may be the closest interpretation to Twain’s original intent. " +
                    "His performance captures the excitement and confusion of adolescence and adventure. Best of all, the immediacy of Wood’s energetic reading sweeps listeners up and makes them feel as though they’re along for the ride, as Huck and Jim push their raft toward freedom.\n" +
                    "\n" +
                    "Public Domain (P)2010 Audible, Inc.",
            coverImage = "https://i.pinimg.com/564x/82/bc/93/82bc931dfe340d06c4b6ca3efdbbe4bd.jpg",
            localCoverImagePath = R.drawable.the_adventures_of_huckleberry_finn,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 21,
            title = "The Iliad",
            author = "Homer",
            publicationYear = -700,
            genres = listOf("Epic","Mythology"),
            description = "An ancient Greek epic poem about the Trojan War and the hero Achilles.",
            longDescription = "2024 Audie Award Winner for Literary Fiction & Classics\n" +
                    "\n" +
                    "When Emily Wilson’s translation of The Odyssey appeared in 2017―rendering the ancient poem in contemporary language that was “fresh, unpretentious and lean” (Madeline Miller, Washington Post)―critics lauded it as “a revelation” (Susan Chira, New York Times) and “a cultural landmark” (Charlotte Higgins, Guardian) that would forever change how Homer is read in English. " +
                    "Now Wilson has returned with an equally revelatory translation of Homer’s other great epic―the most revered war poem of all time.\n" +
                    "\n" +
                    "Brought to life in vivid audio form, this crisp verse translation of The Iliad roars with the clamor of arms, the bellowing boasts of victors, the fury and grief of loss, and the anguished cries of dying men. " +
                    "It sings, too, of the sublime magnitude of the world―the fierce beauty of nature and deities’ grand schemes beyond the ken of mortals.\n" +
                    "\n" +
                    "Wilson’s musical iambic pentameter verse is brought to life in the evocative voice of narrator and Broadway legend Audra McDonald. " +
                    "In her thrilling reading, this magical and often horrifying tale gallops at a pace befitting its legendary battle scenes. " +
                    "The culmination of a decade of intense engagement with antiquity’s most surpassingly beautiful and emotionally complex poetry, Wilson’s Iliad now gives us a complete Homer for our generation.\n" +
                    "\n" +
                    "PLEASE NOTE: When you purchase this title, the accompanying PDF will be available in your Audible Library along with the audio.",
            coverImage = "https://i.pinimg.com/564x/ce/6d/7c/ce6d7c97763fd6f07980fdae8485ce89.jpg",
            localCoverImagePath = R.drawable.the_iliad,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 22,
            title = "Leviathan",
            author = "Scott Westerfeld",
            publicationYear = 1651,
            genres = listOf("Fantasy","Adventure"),
            description = "A war story.",
            longDescription = "It is the cusp of World War I, and all the European powers are arming up. " +
                    "The Austro-Hungarians and Germans have their Clankers, steam-driven iron machines loaded with guns and ammunition. " +
                    "The British Darwinists employ fabricated animals as their weaponry. " +
                    "The Leviathan is a living airship, the most formidable airbeast in the skies of Europe.\n" +
                    "Aleksandar Ferdinand, prince of the Austro-Hungarian Empire, is on the run. " +
                    "His own people have turned on him. His title is worthless. " +
                    "All he has is a battle-torn Stormwalker and a loyal crew of men.\n" +
                    "\n" +
                    "Deryn Sharp is a commoner, a girl disguised as a boy in the British Air Service. She's a brilliant airman. " +
                    "But her secret is in constant danger of being discovered.\n" +
                    "\n" +
                    "With the Great War brewing, Alek's and Deryn's paths cross in the most unexpected way - taking them both aboard the Leviathan on a fantastical, around-the-world adventure. " +
                    "One that will change both their lives forever.",
            coverImage = "https://i.pinimg.com/564x/8b/c5/d0/8bc5d08aa1ba3d0cf470a322f5d742ad.jpg",
            localCoverImagePath = R.drawable.leviathan,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 23,
            title = "Don Quixote",
            author = "Miguel de Cervantes",
            publicationYear = 1605,
            genres = listOf("Classic", "Satire"),
            description = "A satirical novel about a deluded knight and his faithful squire, Sancho Panza.",
            longDescription = "Don Quixote is the classic story. " +
                    "Called the first modern novel, this marvelous book has stood the test of time to become irrevocably intertwined with the fabric of society. " +
                    "Sixteenth-century Spanish gentleman Don Quixote, fed by his own delusional fantasies, takes to the road in search of chivalrous adventures. " +
                    "But his quest leads to more trouble than triumph. " +
                    "At once humorous, romantic, and sad, Don Quixote is a literary landmark. " +
                    "This fresh edition, by award-winning translator Edith Grossman, brings the tale to life as never before.",
            coverImage = "https://i.pinimg.com/564x/d0/d7/8e/d0d78e71834a094a4b6527539c49faa3.jpg",
            localCoverImagePath = R.drawable.don_quixote,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 24,
            title = "Frankenstein",
            author = "Mary Shelley",
            publicationYear = 1818,
            genres = listOf("Gothic", "Science Fiction"),
            description = "A novel about the creation of a monster and the consequences of playing god.",
            longDescription = "",
            coverImage = "https://i.pinimg.com/564x/94/37/81/9437811edf3de19193848c78a576a4a9.jpg",
            localCoverImagePath = R.drawable.frankenstein,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 25,
            title = "Alice's Adventures in Wonderland",
            author = "Lewis Carroll",
            publicationYear = 1865,
            genres = listOf("Fantasy", "Children's Literature"),
            description = "A whimsical tale about a girl named Alice who falls into a magical world.",
            longDescription = "Audie Award Finalist, Solo Narration - Male, 2013\n" +
                    "\n" +
                    "Audie Award Finalist, Classic, 2013\n" +
                    "\n" +
                    "Narrator Dan Stevens (Downton Abbey) presents an uncanny performance of Mary Shelley's timeless gothic novel, an epic battle between man and monster at its greatest literary pitch. " +
                    "In trying to create life, the young student Victor Frankenstein unleashes forces beyond his control, setting into motion a long and tragic chain of events that brings Victor to the very brink of madness. " +
                    "How he tries to destroy his creation, as it destroys everything Victor loves, is a powerful story of love, friendship, scientific hubris, and hor",
            coverImage = "https://i.pinimg.com/564x/fa/a5/9c/faa59c3617b6f821f48f40b67f653620.jpg",
            localCoverImagePath = R.drawable.alice_s_adventures_in_wonderland,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 26,
            title = "The Little Prince",
            author = "Antoine de Saint-Exupéry",
            publicationYear = 1943,
            genres = listOf("Fable","Children's Literature"),
            description = "A philosophical novella about a young prince's journey through the universe.",
            longDescription = "A timeless classic. " +
                    "Few stories are as widely read and as universally cherished by children and adults alike as The Little Prince. " +
                    "The perfect audio companion, this Bolinda edition will capture the hearts of listeners of all ages.\n" +
                    "The book has been translated into more than 160 languages and, to date has sold more than 50 million copies worldwide. " +
                    "It is one of the top 50 best-selling books. " +
                    "It has been adapted into a movie musical by Lerner and Loewe, two different operas, as well as into an animated series. " +
                    "It is often used as a beginner's book for French language students.\n" +
                    "\n" +
                    "A pilot stranded in the desert awakes one morning to see, standing before him, the most extraordinary little fellow. \"Please,\" asks the stranger, \"draw me a sheep.\" " +
                    "And the pilot realizes that when life's events are too difficult to understand, there is no choice but to succumb to their mysteries. He pulls out pencil and paper... " +
                    "And thus begins this wise and enchanting fable that, in teaching the secret of what is really important in life, has changed forever the world for its readers.",
            coverImage = "https://i.pinimg.com/564x/48/9a/af/489aaf3d176759902217b89899162a2b.jpg",
            localCoverImagePath = R.drawable.the_little_prince,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 27,
            title = "The Book Thief",
            author = "Markus Zusak",
            publicationYear = 2005,
            genres = listOf("Historical Fiction", "War"),
            description = "A story of a girl living in Nazi Germany, narrated by Death.",
            longDescription = "Don’t miss Bridge of Clay, Markus Zusak’s first novel since The Book Thief.\n" +
                    "\n" +
                    "The extraordinary number-one New York Times best seller that is now a major motion picture, Markus Zusak's unforgettable story is about the ability of books to feed the soul. " +
                    "Nominated as one of America's best-loved novels by PBS’s The Great American Read.\n" +
                    "\n" +
                    "When Death has a story to tell, you listen. It is 1939. Nazi Germany. The country is holding its breath. " +
                    "Death has never been busier, and will become busier still. Liesel Meminger is a foster girl living outside of Munich, who scratches out a meager existence for herself by stealing when she encounters something she can’t resist - books. " +
                    "With the help of her accordion-playing foster father, she learns to read and shares her stolen books with her neighbors during bombing raids as well as with the Jewish man hidden in her basement.\n" +
                    "\n" +
                    "In superbly crafted writing that burns with intensity, award-winning author Markus Zusak, author of I Am the Messenger, has given us one of the most enduring stories of our time.\n" +
                    "\n" +
                    "“The kind of book that can be life-changing.” (The New York Times)\n" +
                    "\n" +
                    "“Deserves a place on the same shelf with The Diary of a Young Girl by Anne Frank.” (USA Today)",
            coverImage = "https://i.pinimg.com/564x/a9/c0/a4/a9c0a4a4c7f78cda7bbdc974149e6155.jpg",
            localCoverImagePath = R.drawable.the_book_thief,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 28,
            title = "Slaughterhouse-Five",
            author = "Kurt Vonnegut",
            publicationYear = 1969,
            genres = listOf("Satire", "Science Fiction"),
            description = "An anti-war novel that mixes science fiction and dark humor.",
            longDescription = "Slaughterhouse-Five is the now famous parable of Billy Pilgrim, a World War II veteran and POW who has, in the later stage of his life, become \"unstuck in time\" and who experiences at will (or unwillingly) all known events of his chronology out of order and sometimes simultaneously.\n" +
                    "\n" +
                    "Traumatized by the bombing of Dresden at the time he had been imprisoned, Pilgrim drifts through all events and history, sometimes deeply implicated, sometimes a witness. " +
                    "He is surrounded by Vonnegut's usual large cast of continuing characters (notably here the hack science fiction writer Kilgore Trout and the alien Tralfamadorians, who oversee his life and remind him constantly that there is no causation, no order, no motive to existence). " +
                    "The \"unstuck\" nature of Pilgrim's experience may constitute an early novelistic use of what we now call post-traumatic stress disorder; then again, Pilgrim's aliens may be as \"real\" as Dresden is real to him.\n" +
                    "\n" +
                    "Struggling to find some purpose, order, or meaning to his existence and humanity's, Pilgrim meets the beauteous and mysterious Montana Wildhack (certainly the author's best character name), has a child with her, and drifts on some supernal plane, finally, in which Kilgore Trout, the Tralfamadorians, Montana Wildhack, and the ruins of Dresden do not merge but rather disperse through all planes of existence.\n" +
                    "\n" +
                    "Slaughterhouse-Five was hugely successful, brought Vonnegut an enormous audience, was a finalist for the National Book Award and a best seller, and remains four decades later as timeless and shattering a war fiction as Catch-22, with which it stands as the two signal novels of their riotous and furious decade.",
            coverImage = "https://i.pinimg.com/564x/e9/6b/cd/e96bcdaffaa4211329d16a8463286c1e.jpg",
            localCoverImagePath = R.drawable.slaughterhouse_five,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 29,
            title = "The Grapes of Wrath",
            author = "John Steinbeck",
            publicationYear = 1939,
            genres = listOf("Historical Fiction", "Social Commentary"),
            description = "A novel about the plight of migrant workers during the Great Depression.",
            longDescription = "John Steinbeck's powerful evocation of the suffering and hardship caused by the Great Depression, and a panoramic vision of the struggle for the American Dream, The Grapes of Wrath includes a critical introduction by Robert DeMott in Penguin Modern Classics. \"I've done my damndest to rip a reader's nerves to rags, I don't want him satisfied\".\n" +
                    "\n" +
                    "Shocking and controversial when it was first published in 1939, Steinbeck's Pulitzer prize-winning epic The Grapes of Wrath remains his undisputed masterpiece. " +
                    "Set against the background of Dust Bowl Oklahoma and Californian migrant life, it tells of Tom Joad and his family, who, like thousands of others, are forced to travel west in search of the promised land. " +
                    "Their story is one of false hopes, thwarted desires, and broken dreams, yet out of their suffering Steinbeck created a drama that is intensely human, yet majestic in its scale and moral vision. " +
                    "Adapted into a celebrated film directed by John Ford, and starring Henry Fonda, The Grapes of Wrath is an eloquent tribute to the endurance and dignity of the human spirit.\n" +
                    "\n" +
                    "John Steinbeck (1902-68), winner of the Pulitzer Prize and the Nobel Prize for literature, is remembered as one of the greatest and best-loved American writers of the 20th century. " +
                    "During the Second World War Steinbeck served as a war correspondent, with his collected dispatches published as Once There Was a War (1958); in 1945 he was awarded the Norwegian Cross of Freedom for his novel The Moon is Down (1942), a portrayal of Resistance efforts in northern Europe. " +
                    "His best-known works include the epics The Grapes of Wrath (1939) and East of Eden (1952), and his tragic novella Of Mice and Men (1937). " +
                    "John Steinbeck's complete works are published in Penguin Modern Classics. " +
                    "If you liked The Grapes of Wrath, you might enjoy East of Eden, also available in Penguin Classics.",
            coverImage = "https://i.pinimg.com/564x/11/7a/5a/117a5a23afa8785306fd1aa67b7d1386.jpg",
            localCoverImagePath = R.drawable.the_grapes_of_wrath,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 30,
            title = "Fahrenheit 451",
            author = "Ray Bradbury",
            publicationYear = 1953,
            genres = listOf("Dystopian","Science Fiction"),
            description = "A dystopian novel depicting a future society where books are banned.",
            longDescription = "Earphones Award Winner (AudioFile Magazine)\n" +
                    "\n" +
                    "Ray Bradbury's internationally acclaimed novel Fahrenheit 451 is a masterwork of 20th-century literature set in a bleak, dystopian future, narrated here by Academy Award-winning actor Tim Robbins.\n" +
                    "\n" +
                    "Guy Montag is a fireman. " +
                    "In his world, where television rules and literature is on the brink of extinction, firemen start fires rather than put them out. His job is to destroy the most illegal of commodities, the printed book, along with the houses in which they are hidden. " +
                    "Montag never questions the destruction and ruin his actions produce, returning each day to his bland life and wife, Mildred, who spends all day with her television \"family\". " +
                    "But then he meets an eccentric young neighbor, Clarisse, who introduces him to a past where people didn’t live in fear and to a present where one sees the world through the ideas in books instead of the mindless chatter of television. " +
                    "When Mildred attempts suicide and Clarisse suddenly disappears, Montag begins to question everything he has ever known. " +
                    "He starts hiding books in his home, and when his pilfering is discovered, the fireman has to run for his life.",
            coverImage = "https://i.pinimg.com/564x/7b/63/3d/7b633dfb1ac4ff8dd2b47db80eeecf58.jpg",
            localCoverImagePath = R.drawable.fahrenheit_451,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 31,
            title = "The Lord of the Flies",
            author = "William Golding",
            publicationYear = 1954,
            genres = listOf("Dystopian","Psychological Fiction"),
            description = "A novel about a group of British boys stranded on an uninhabited island.",
            longDescription = "Get ready for an adventure tale in its purest form, a thrilling and elegantly told account of a group of British schoolboys marooned on a tropical island. " +
                    "Alone in a world of uncharted possibilities, devoid of adult supervision or rules, the boys begin to forge their own society, their own rules, their own rituals. " +
                    "With this seemingly romantic premise, and through the seemingly innocent acts of children, Golding exposes the duality of human nature itself: the dark, eternal divide between order and chaos, intellect and instinct, structure and savagery.",
            coverImage = "https://i.pinimg.com/564x/b5/78/e0/b578e094fcdee9bc43e9e10bf783e71c.jpg",
            localCoverImagePath = R.drawable.the_lor_of_the_files,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 32,
            title = "The Hitchhiker's Guide to the Galaxy",
            author = "Douglas Adams",
            publicationYear = 1979,
            genres = listOf("Science Fiction", "Comedy"),
            description = "A comedic science fiction series about the misadventures of Arthur Dent.",
            longDescription = "Seconds before the Earth is demolished to make way for a galactic freeway, Arthur Dent is plucked off the planet by his friend Ford Prefect, a researcher for the revised edition of The Hitchhiker's Guide to the Galaxy who, for the last 15 years, has been posing as an out-of-work actor.\n" +
                    "Together this dynamic pair begin a journey through space aided by quotes from The Hitchhiker's Guide (\"A towel is about the most massively useful thing an interstellar hitchhiker can have.\") and a galaxy full of fellow travelers: Zaphod Beeblebrox, the two-headed, three-armed ex-hippie and totally out-to-lunch president of the galaxy; Trillian, Zaphod's girlfriend (formally Tricia McMillan), whom Arthur tried to pick up at a cocktail party once upon a time zone; Marvin, a paranoid, brilliant, and chronically depressed robot; and Veet Voojagig, a former graduate student who is obsessed with the disappearance of all the ballpoint pens he bought over the years.\n" +
                    "\n" +
                    "Where are these pens? Why are we born? Why do we die? " +
                    "Why do we spend so much time in between wearing digital watches? " +
                    "For all the answers stick your thumb to the stars. And don't forget to bring a towel!",
            coverImage = "https://i.pinimg.com/564x/af/65/af/af65afc94ecd442c25d93a4dcd86d1f1.jpg",
            localCoverImagePath = R.drawable.the_hitchhiker_s_guide_to_the_galaxy,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 33,
            title = "A Tale of Two Cities",
            author = "Charles Dickens",
            publicationYear = 1859,
            genres = listOf("Historical Fiction","Classic"),
            description = "A historical novel set during the French Revolution, exploring themes of sacrifice and resurrection.",
            longDescription = "This Audible Exclusive production revisits one of Dickens' popular novels: A Tale of Two Cities. In Dickens' driving narrative we see many themes that permeate life today as well as characters who provide a window into the past. " +
                    "This, coupled with Simon Callow's expert narration, is a treat for those new to Dickens and lifelong fans alike. " +
                    "Featuring an exclusive introduction written by Callow, whose passion for Dickens shines through.\n" +
                    "\n" +
                    "This release marks the start of the Dickens Collection, an exclusive series of unmissable performances available throughout 2018.\n" +
                    "\n" +
                    "About the book\n" +
                    "\n" +
                    "'It was the best of times, it was the worst of times.'\n" +
                    "\n" +
                    "So begins Charles Dickens' most famous historical drama: a gripping tale of war, social injustice and the choice between darkness and light. " +
                    "After being unjustly imprisoned for 18 years, French doctor Manette is released from the Bastille jail in Paris and embarks upon a journey to London in the hope of finding the daughter he never met. " +
                    "Young Lucie Manette is a pretty and dutiful girl who soon attracts the attention of two very different gentlemen. " +
                    "Now reunited with the father she believed to be dead, happiness appears to be within reach. But as they are all drawn back to the bloodstained streets of Paris, misery and the threat of La Guillotine loom once again.\n" +
                    "\n" +
                    "In Callow's introduction, we discover how Dickens' own volatile personal circumstances of the time are mirrored in A Tale of Two Cities. " +
                    "He tells of Dickens' personal feuds and explains why this novel sees Dickens at his most theatrical.\n" +
                    "\n" +
                    "About the author\n" +
                    "\n" +
                    "With his father incarcerated, Charles Dickens had to abandon his studies at a young age and set to work in a factory so as to support himself. " +
                    "Despite his short-lived education, Dickens went on to write 15 novels, various articles, novellas and short stories. " +
                    "He lectured and led campaigns for children's rights and education and arguably became the ultimate self-made man. " +
                    "Dickens had strong values, and they pervade A Tale of Two Cities, which can be seen as not only an inspirational text but one which will continue to stand the test of time.\n" +
                    "\n" +
                    "About the narrator\n" +
                    "\n" +
                    "Simon Callow is a multi-award-winning actor, writer and theatre director. " +
                    "He is best known for his performances in Amadeus, Ace Ventura: When Nature Calls and Four Weddings and a Funeral. " +
                    "Simon has vast stage experience and clearly loves what he does. " +
                    "His fervour began at a young age working as box office staff, and it wasn't long before he made the transition from behind the scenes to centre stage - never looking back.\n" +
                    "\n" +
                    "Callow is also known for his literary talents and has published various biographies including those of Oscar Wilde, Charles Laughton and Orson Welles. " +
                    "He has narrated over 20 audiobooks and brings his wealth of experience and characteristic charm to this exciting performance.",
            coverImage = "https://i.pinimg.com/564x/6b/d7/b6/6bd7b60745a1427d989819cf7a1fa52f.jpg",
            localCoverImagePath = R.drawable.a_tale_of_two_cities,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 34,
            title = "The Chronicles of Narnia",
            author = "C.S. Lewis",
            publicationYear = 1950,
            genres = listOf("Fantasy","Children's Literature"),
            description = "A series of fantasy novels set in the magical land of Narnia.",
            longDescription = "For over 60 years, readers of all ages have been enchanted by the magical realms, the epic battles between good and evil, and the unforgettable creatures of Narnia.\n" +
                    "\n" +
                    "This box set includes all seven titles in The Chronicles of Narnia - The Magician's Nephew; The Lion, the Witch, and the Wardrobe; The Horse and His Boy; Prince Caspian; The Voyage of the Dawn Treader; The Silver Chair; and The Last Battle.",
            coverImage = "https://i.pinimg.com/564x/aa/eb/0a/aaeb0ae993357d7ffba9aa5cbcf8a04b.jpg",
            localCoverImagePath = R.drawable.the_chronicles_of_narnia,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 35,
            title = "The Handmaid's Tale",
            author = "Margaret Atwood",
            publicationYear = 1985,
            genres = listOf("Dystopian","Feminist Fiction"),
            description = "A dystopian novel set in a totalitarian society where women are subjugated.",
            longDescription = "Audie Award, Fiction, 2013\n" +
                    "\n" +
                    "Margaret Atwood's popular dystopian novel The Handmaid's Tale explores a broad range of issues relating to power, gender, and religious politics. " +
                    "Multiple Golden Globe award-winner Claire Danes (Romeo and Juliet, The Hours) gives a stirring performance of this classic in speculative fiction, one of the most powerful and widely read novels of our time.\n" +
                    "\n" +
                    "After a staged terrorist attack kills the President and most of Congress, the government is deposed and taken over by the oppressive and all-controlling Republic of Gilead. " +
                    "Offred, now a Handmaid serving in the household of the enigmatic Commander and his bitter wife, can remember a time when she lived with her husband and daughter and had a job, before she lost even her own name. Despite the danger, Offred learns to navigate the intimate secrets of those who control her every move, risking her life in breaking the rules in hopes of ending this oppression.\n" +
                    "\n" +
                    "Cover Art by Fred Marcellino. Used with permission of Pippin Properties, Inc.",
            coverImage = "https://i.pinimg.com/564x/7a/fe/33/7afe33fbafd769cdc7f9d32c329681d3.jpg",
            localCoverImagePath = R.drawable.the_handmaid_s_tale,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 36,
            title = "The Name of the Rose",
            author = "Umberto Eco",
            publicationYear = 1980,
            genres = listOf("Historical Fiction","Mystery"),
            description = "A medieval mystery novel set in an Italian monastery.",
            longDescription = "The international best seller! A masterful gothic thriller set against the turbulence of medieval Italy.\n" +
                    "\n" +
                    "The year is 1327. Franciscans in a wealthy Italian abbey are suspected of heresy, and Brother William of Baskerville arrives to investigate. " +
                    "But his delicate mission is suddenly overshadowed by seven bizarre deaths that take place in seven days and nights of apocalyptic terror.\n" +
                    "\n" +
                    "Brother William turns detective, and a uniquely deft one at that. " +
                    "His tools are the logic of Aristotle, the theology of Aquinas, the empirical insights of Roger Bacon - all sharpened to a glistening edge by his wry humor and ferocious curiosity. " +
                    "He collects evidence, deciphers secret symbols and coded manuscripts, and digs into the eerie labyrinth of the abbey where \"the most interesting things happen at night\".\n" +
                    "\n" +
                    "As Brother William goes about unraveling the mystery of what happens at the abbey by day and by night, listeners step into a brilliant re-creation of the 14th century, with its dark superstitions and wild prejudices, its hidden passions and sordid intrigues. " +
                    "Virtuoso storyteller Umberto Eco conjures up a gloriously rich portrait of this world with such grace, ease, wit, and love that you will become utterly intoxicated with the place and time.",
            coverImage = "https://i.pinimg.com/564x/46/74/1f/46741fa4d7b21e5157a2444cd7a875d1.jpg",
            localCoverImagePath = R.drawable.the_name_of_the_rose,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 37,
            title = "The Trial",
            author = "Franz Kafka",
            publicationYear = 1925,
            genres = listOf("Absurdist Fiction","Existential"),
            description = "A surreal novel exploring themes of guilt, law, and justice.",
            longDescription = "If Max Brod had obeyed Franz Kafka's dying request, Kafka's unpublished manuscripts would have been burned, unread. Fortunately, Brod ignored his friend's wishes and published The Trial, which became the author's most famous work. Now Kafka's enigmatic novel regains its humor and stylistic elegance in a new translation based on the restored original manuscript.\n" +
                    "Thirty-year-old Josef K., a financial officer in a European city bank, is suddenly arrested. He is subjected to hearings, questioning, and visits from officials. Defending his innocence against charges that are never explained to him, he watches his life dissolve into absurdity. Whether read as an existential tale or a parable, this haunting story stands out as one of the great novels of our time.\n" +
                    "\n" +
                    "Breon Mitchell, a professor of Germanic Studies and Comparative Literature at Indiana University, has received national awards for his literary translations. " +
                    "The renewed energy and power of this classic work are complemented by veteran narrator George Guidall's superb performance.",
            coverImage = "https://i.pinimg.com/564x/19/96/c7/1996c72b66915b98e4585dc32e2a7a79.jpg",
            localCoverImagePath = R.drawable.the_trial,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 38,
            title = "The Kite Runner",
            author = "Khaled Hosseini",
            publicationYear = 2003,
            genres = listOf("Historical Fiction","Drama"),
            description = "A novel about friendship, redemption, and the impact of war in Afghanistan.",
            longDescription = "Taking us from Afghanistan in the final days of its monarchy to the present, The Kite Runner is the unforgettable and beautifully told story of the friendship between two boys growing up in Kabul. " +
                    "Raised in the same household and sharing the same wet nurse, Amir and Hassan grow up in different worlds: Amir is the son of a prominent and wealthy man, while Hassan, the son of Amir's father's servant, is a Hazara - a shunned ethnic minority. " +
                    "Their intertwined lives, and their fates, reflect the eventual tragedy of the world around them. " +
                    "When Amir and his father flee the country for a new life in California, Amir thinks that he has escaped his past. " +
                    "And yet he cannot leave the memory of Hassan behind him.\n" +
                    "The Kite Runner is a novel about friendship and betrayal, and about the price of loyalty. " +
                    "It is about the bonds between fathers and sons, and the power of fathers over sons - their love, their sacrifices, and their lies. " +
                    "Written against a backdrop of history that has not been told in fiction before, The Kite Runner describes the rich culture and beauty of a land in the process of being destroyed. " +
                    "But through the devastation, Khaled Hosseini offers hope for redemption.",
            coverImage = "https://i.pinimg.com/564x/af/4f/8b/af4f8bc196aaff6f0c00b070660d1a19.jpg",
            localCoverImagePath = R.drawable.the_kite_runner,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 39,
            title = "The Pillars of the Earth",
            author = "Ken Follett",
            publicationYear = 1989,
            genres = listOf("Historical Fiction", "Adventure"),
            description = "An epic historical novel set in 12th-century England, centered around the construction of a cathedral.",
            longDescription = "The Pillars of the Earth tells the story of Philip, prior of Kingsbridge, a devout and resourceful monk driven to build the greatest Gothic cathedral the world has known...of Tom, the mason who becomes his architect - a man divided in his soul...of the beautiful, elusive Lady Aliena, haunted by a secret shame...and of a struggle between good and evil that will turn church against state, and brother against brother.\n" +
                    "\n" +
                    "A spellbinding epic tale of ambition, anarchy, and absolute power set against the sprawling medieval canvas of 12th century England, this is Ken Follett's historical masterpiece.",
            coverImage = "https://i.pinimg.com/564x/67/d3/35/67d33537b5e081e3d7fcc82985f2c81f.jpg",
            localCoverImagePath = R.drawable.the_pillars_of_the_earth,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 40,
            title = "The Shadow of the Wind",
            author = "Carlos Ruiz Zafón",
            publicationYear = 2001,
            genres = listOf("Mystery","Gothic"),
            description = "A mystery novel set in post-war Barcelona, revolving around a forgotten book and its author.",
            longDescription = "Barry Award\n" +
                    "\n" +
                    "Book Sense Book of the Year\n" +
                    "\n" +
                    "Borders Original Voices Award\n" +
                    "\n" +
                    "Gumshoe Award\n" +
                    "\n" +
                    "Horror Guild Award\n" +
                    "\n" +
                    "Joseph-Beth and Davis-Kidd Booksellers Fiction Award\n" +
                    "\n" +
                    "NYPL Books to Remember\n" +
                    "\n" +
                    "Barcelona, 1945: Just after the war, a great world city lies in shadow, nursing its wounds, and a boy named Daniel awakes on his 11th birthday to find that he can no longer remember his mother's face. " +
                    "To console his only child, Daniel's widowed father, an antiquarian book dealer, initiates him into the secret of the Cemetery of Forgotten Books, a library tended by Barcelona's guild of rare-book dealers as a repository for books forgotten by the world, waiting for someone who will care about them again.\n" +
                    "\n" +
                    "Daniel's father coaxes him to choose a volume from the spiraling labyrinth of shelves, one that, it is said, will have a special meaning for him. And Daniel so loves the novel he selects, The Shadow of the Wind by one Julian Carax, that he sets out to find the rest of Carax's work. " +
                    "To his shock, he discovers that someone has been systematically destroying every copy of every book this author has written. " +
                    "In fact, he may have the last one in existence. " +
                    "Before Daniel knows it, his seemingly innocent quest has opened a door into one of Barcelona's darkest secrets, an epic story of murder, magic, madness, and doomed love.\n" +
                    "\n" +
                    "An uncannily absorbing historical mystery, a heart-piercing romance, and a moving homage to the mystical power of books, The Shadow of the Wind is a triumph of the storyteller's art.\n" +
                    "\n" +
                    "Translated by Lucia Graves.",
            coverImage = "https://i.pinimg.com/564x/99/6b/1a/996b1a484ca909f8f92f71451f8913f7.jpg",
            localCoverImagePath = R.drawable.the_shadow_of_the_wind,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 41,
            title = "The Secret Garden",
            author = "Frances Hodgson Burnett",
            publicationYear = 1911,
            genres = listOf("Children's Literature","Classic"),
            description = "A classic children's novel about a young girl who discovers a hidden garden.",
            longDescription = "Read by actress, singer, blogger and author Carrie Hope Fletcher, Frances Hodgson Burnett’s delightful novel The Secret Garden is given new life. Delve in to the tranquil world of Burnett’s story as you follow the journey of her young protagonist, Mary Lennox, on her journey through her childhood. " +
                    "First published in 1911, the novel is a much-cited ‘classic’ of English children’s literature. " +
                    "It is, however, as much for adults as for anyone else. " +
                    "An uplifting and light-hearted listen, this is the ideal summer escape.\n" +
                    "\n" +
                    "Mary Lennox starts her life as an unhappy victim of circumstance. " +
                    "After the loss of her parents, she moves to rural Yorkshire to live with a distant uncle where she resents the wildness of the countryside. " +
                    "At first, she struggles to find a place in this new existence. " +
                    "Although unsure about her surroundings and its occupants, through the gentle guidance of the maid she gradually becomes interested in the story of Mrs Craven who apparently used to spend her time in a garden at the house, the key to which has vanished. Mary makes it her quest to find and explore the possibilities it holds. Her journey sees her change, befriending a host of loveable characters as the garden begins to cast its spell on her.\n" +
                    "\n" +
                    "About the author\n" +
                    "\n" +
                    "Born in 1849, Burnett moved to America in 1865 where she would live, on and off, for the rest of her life. " +
                    "She was well travelled, living in Paris for a few years before buying a house in England (where she wrote The Secret Garden) and finally moving to New York. " +
                    "She is best known for her children’s novels such as Little Lord Fauntleroy and A Little Princess but was also a prodigious writer of adult fiction. " +
                    "It is this, which perhaps explains how she was able to merge and cater for both audiences with such success in The Secret Garden.\n" +
                    "\n" +
                    "About the narrator\n" +
                    "\n" +
                    "Carrie Hope Fletcher is an award-winning actress, best-selling author, musical theatre star and social media influencer. Carrie has starred in Les Misérables, and Heathers on London’s West End and is author to four best-selling books: All I Know Now, On the Other Side, All That She Can See and When The Curtain Falls, her next title is called In The Time We Lost. " +
                    "Carrie has also released a debut solo album, When The Curtains Fall. " +
                    "The album features Carrie’s most-loved musical theatre songs, covered in a unique and modern way. " +
                    "Alongside her acting, writing and music career, Carrie has an established online presence with over 528,000 followers on Twitter, 492,000 on Instagram and over 652,000 subscribers on her YouTube channel.\n" +
                    "\n" +
                    "Cover Credit: Aitch at Central Illustration",
            coverImage = "https://i.pinimg.com/564x/53/f1/20/53f120f54a2ab1598c3f7d4d3782abec.jpg",
            localCoverImagePath = R.drawable.the_secret_garden,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 42,
            title = "The Giver",
            author = "Lois Lowry",
            publicationYear = 1993,
            genres = listOf("Dystopian","Young Adult"),
            description = "A dystopian novel about a society with strict control over emotions and memories.",
            longDescription = "The Giver, the 1994 Newbery Medal winner, has become one of the most influential novels of our time. " +
                    "The haunting story centers on twelve-year-old Jonas, who lives in a seemingly ideal, if colorless, world of conformity and contentment. " +
                    "Not until he is given his life assignment as the Receiver of Memory does he begin to understand the dark, complex secrets behind his fragile community. " +
                    "Lois Lowry has written three companion novels to The Giver, including Gathering Blue, Messenger, and Son.\n" +
                    "\n" +
                    "©1993 Lois Lowry (P)1993 Random House, Inc., Listening Library, An Imprint Of Random House Audio Publishing Group",
            coverImage = "https://i.pinimg.com/564x/3a/13/0e/3a130ef7ffe5be4eb73a87c1b1b85b92.jpg",
            localCoverImagePath = R.drawable.the_giver,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 43,
            title = "The Metamorphosis",
            author = "Franz Kafka",
            publicationYear = 1915,
            genres = listOf("Absurdist Fiction","Existential"),
            description = "A novella about a man who wakes up one morning transformed into a giant insect.",
            longDescription = "“One morning, as Gregor Samsa was waking up from anxious dreams, he discovered that in bed he had been changed into a monstrous verminous bug.”\n" +
                    "With this startling, bizarre, yet surprisingly funny first sentence, Kafka begins his masterpiece, The Metamorphosis. " +
                    "It is the story of a young traveling salesman who, transformed overnight into a giant, beetle-like insect, becomes an object of disgrace to his family, an outsider in his own home, a quintessentially alienated man. Rather than being surprised at the transformation, the members of his family despise it as an impending burden upon themselves.\n" +
                    "\n" +
                    "A harrowing - though absurdly comic - meditation on human feelings of inadequacy, guilt, and isolation, The Metamorphosis has taken its place as one of the most widely read and influential works of 20th-century fiction. " +
                    "As W. H. Auden wrote, “Kafka is important to us because his predicament is the predicament of modern man.”\n" +
                    "\n" +
                    "FRANZ KAFKA (1883–1924), one of the major fiction writers of the twentieth century, was born to a middle-class German-speaking Jewish family in Prague. " +
                    "His unique body of writing, much of which is incomplete and was mainly published posthumously, is considered by some people to be among the most influential in Western literature, inspiring such writers as Albert Camus, Rex Warner, and Samuel Beckett.",
            coverImage = "https://i.pinimg.com/564x/23/e9/93/23e99354e39803b73701b6cfaf8749fb.jpg",
            localCoverImagePath = R.drawable.the_metamorphosis,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 44,
            title = "Gone with the Wind",
            author = "Margaret Mitchell",
            publicationYear = 1936,
            genres = listOf("Historical Fiction","Romance"),
            description = "A historical novel set during the American Civil War, centered around Scarlett O'Hara.",
            longDescription = "",
            coverImage = "https://i.pinimg.com/564x/1a/87/9a/1a879ac1fefdee0f1f8dd9c0afc9b60a.jpg",
            localCoverImagePath = R.drawable.gone_with_the_wind,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 45,
            title = "The Wind in the Willows",
            author = "Kenneth Grahame",
            publicationYear = 1908,
            genres = listOf("Children's Literature","Fantasy"),
            description = "A children's novel about the adventures of anthropomorphic animals.",
            longDescription = "Winner of the Pulitzer Prize for Literature, Margaret Mitchell's great novel of the South is one of the most popular books ever written. " +
                    "Within six months of its publication in 1936, Gone With the Wind had sold a million copies. " +
                    "To date, it has been translated into 25 languages, and more than 28 million copies have been sold.\n" +
                    "Here are the characters that have become symbols of passion and desire: darkly handsome Rhett Butler and flirtatious Scarlett O'Hara. " +
                    "Behind them stand their gentler counterparts: Ashley Wilkes and Melanie Hamilton. " +
                    "As the lives and affairs of these absorbing characters play out against the tumult of the Civil War, Gone With the Wind reaches dramatic heights that have swept generations of fans off their feet.\n" +
                    "\n" +
                    "Having lived in Atlanta for many years, narrator Linda Stephens has an authentic ear for the dialects of that region. " +
                    "Get ready to hear Gone With the Wind exactly as it was written: every word beautifully captured in a spectacular unabridged audio production.",
            coverImage = "https://i.pinimg.com/564x/81/38/0b/81380b23388651b772ee1a3b85ef896b.jpg",
            localCoverImagePath = R.drawable.the_wind_in_the_willows,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 46,
            title = "Dracula",
            author = "Bram Stoker",
            publicationYear = 1897,
            genres = listOf("Gothic","Horror"),
            description = "A Gothic horror novel about the vampire Count Dracula's attempt to move to England.",
            longDescription = "Audie Award, Distinguished Achievement in Production, 2013\n" +
                    "\n" +
                    "Audie Award, Multi-voiced Performance, 2013\n" +
                    "\n" +
                    "Audie Award Nominee, Classic, 2013\n" +
                    "\n" +
                    "Because of the widespread awareness of the story of the evil Transylvanian count and the success of numerous film adaptations that have been created over the years, the modern audience hasn't had a chance to truly appreciate the unknowing dread that readers would have felt when reading Bram Stoker's original 1897 manuscript. " +
                    "Most modern productions employ campiness or sound effects to try to bring back that gothic tension, but we've tried something different. " +
                    "By returning to Stoker's original storytelling structure - a series of letters and journal entries voiced by Jonathan Harker, Dr. Van Helsing, and other characters - with an all-star cast of narrators, we've sought to recapture its originally intended horror and power.\n" +
                    "\n" +
                    "This production of Dracula is presented by what is possibly the best assemblage of narrating talent ever for one audiobook: Emmy Award nominees Alan Cumming and Tim Curry plus an all-star cast of Audie award-winners Simon Vance (The Millenium Trilogy), Katherine Kellgren (Pride and Prejudice and Zombies), Susan Duerden (The Tiger’s Wife), John Lee (Supergods) and customer favorites Graeme Malcolm (Skippy Dies), Steven Crossley (The Oxford Time Travel series), Simon Prebble (The Baroque Cycle), James Adams (Letters to a Young Contrarian), Nicola Barber (The Rose Garden), Victor Villar-Hauser (Fun Inc.), and Marc Vietor (1Q84). " +
                    "These stellar narrators have been cast as follows:\n" +
                    "\n" +
                    "Alan Cumming as Dr. Seward\n" +
                    "Simon Vance as Jonathan Harker\n" +
                    "Katy Kellgren as Mina Murray/Harker\n" +
                    "Susan Duerden as Lucy Westenra\n" +
                    "Tim Curry as Van Helsing\n" +
                    "Graeme Malcolm as Dailygraph correspondent\n" +
                    "Steven Crossley as Zookeeper’s account and reporter\n" +
                    "Simon Prebble as Varna\n" +
                    "James Adams as Patrick Hennessey\n" +
                    "Nicola Barber as Sister Agatha\n" +
                    "Victor Villar-Hauser as Arthur Holmwood\n" +
                    "Marc Vietor as Quincey Morris\n" +
                    "John Lee as Introductory paragraph, various letters",
            coverImage = "https://i.pinimg.com/564x/55/1f/7e/551f7e09a6793a8bd29df7e4759b508c.jpg",
            localCoverImagePath = R.drawable.dracula,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 47,
            title = "The Call of the Wild",
            author = "Jack London",
            publicationYear = 1903,
            genres = listOf("Adventure","Nature"),
            description = "An adventure novel about a domestic dog's life in the wilds of the Yukon.",
            longDescription = "Rediscover one of literature’s most beloved classics, richly reissued in a pivotal new audio recording. " +
                    "Emmy and Tony Award-nominated actor Pablo Schreiber (The Wire, Orange Is the New Black) delivers a stirring performance of Jack London’s fierce yet tender tale of loyalty between man and beast, told from the point of view of a dog.\n" +
                    "\n" +
                    "The Call of the Wild remains one of London’s best-loved novels, often regarded as the crowning masterpiece of a writer whose enduring popularity and prolific output hold a unique place in American literature.\n" +
                    "\n" +
                    "On its face, the novel is the story of Buck, a dog stolen from his comfortable home on a California ranch and shipped to the frozen Yukon to be a trained as a sled dog. " +
                    "But this exhilarating tale of a canine hero’s fight for survival is in fact a vivid depiction of the great gold rush to the Klondike in 1897. " +
                    "Brutal and fierce on one hand, this iconic adventure story is also a heart-warming tale of the tenderness and loyalty between humans and animals, brilliantly told from the latter’s perspective.\n" +
                    "\n" +
                    "Narrator Schreiber, whose previous performance of Brett Easton Ellis’ American Psycho has endeared him to listeners, brings a classic American tale to the ears - and hearts - of a new generation.",
            coverImage = "https://i.pinimg.com/564x/5f/99/6c/5f996cd2f6d632c1039f586f3d8d1d6d.jpg",
            localCoverImagePath = R.drawable.the_call_of_the_wild,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 48,
            title = "The Stand",
            author = "Stephen King",
            publicationYear = 1978,
            genres = listOf("Horror","Post-Apocalyptic"),
            description = "A post-apocalyptic horror novel about a deadly pandemic and its aftermath.",
            longDescription = "This is the way the world ends: with a nanosecond of computer error in a Defense Department laboratory and a million casual contacts that form the links in a chain letter of death.\n" +
                    "\n" +
                    "And here is the bleak new world of the day after: a world stripped of its institutions and emptied of 99 percent of its people. " +
                    "A world in which a handful of panicky survivors choose sides - or are chosen. " +
                    "A world in which good rides on the frail shoulders of the 108-year-old Mother Abagail - and the worst nightmares of evil are embodied in a man with a lethal smile and unspeakable powers: Randall Flagg, the dark man.\n" +
                    "\n" +
                    "In 1978, Stephen King published The Stand, the novel that is now considered to be one of his finest works. " +
                    "But as it was first published, The Stand was incomplete, since more than 150,000 words had been cut from the original manuscript.\n" +
                    "\n" +
                    "Now Stephen King's apocalyptic vision of a world blasted by plague and embroiled in an elemental struggle between good and evil has been restored to its entirety. " +
                    "The Stand: The Complete and Uncut Edition includes material previously deleted, along with new material that King added as he reworked the manuscript for a new generation. " +
                    "It gives us new characters and endows familiar ones with new depths. " +
                    "It has a new beginning and a new ending. " +
                    "What emerges is a gripping work with the scope and moral complexity of a true epic.\n" +
                    "\n" +
                    "For hundreds of thousands of fans who heard The Stand in its original version and wanted more, this new edition is Stephen King's gift. " +
                    "And those who are listening to The Stand for the first time will discover a triumphant and eerily plausible work of the imagination that takes on the issues that will determine our survival.\n" +
                    "\n" +
                    "Cover artwork 2020 CBS Interactive Inc.",
            coverImage = "https://i.pinimg.com/564x/e0/bf/72/e0bf72755d4211338e3b14a5b1791e0b.jpg",
            localCoverImagePath = R.drawable.the_stand,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id = 49,
            title = "The Color Purple",
            author = "Alice Walker",
            publicationYear = 1982,
            genres = listOf("Fiction","Historical"),
            description = "A novel about the life of African-American women in the Southern United States.",
            longDescription = "Winner of the Pulitzer Prize and the National Book Award, this novel about a resilient and courageous woman has become a Broadway show and a cultural phenomenon.\n" +
                    "\n" +
                    "A PBS Great American Read Top 100 Pick\n" +
                    "\n" +
                    "Celie has grown up poor in rural Georgia, despised by the society around her and abused by her own family. " +
                    "She strives to protect her sister, Nettie, from a similar fate, and while Nettie escapes to a new life as a missionary in Africa, Celie is left behind without her best friend and confidante, married off to an older suitor, and sentenced to a life alone with a harsh and brutal husband.\n" +
                    "\n" +
                    "In an attempt to transcend a life that often seems too much to bear, Celie begins writing letters directly to God. " +
                    "The letters, spanning 20 years, record a journey of self-discovery and empowerment guided by the light of a few strong women. " +
                    "She meets Shug Avery, her husband’s mistress and a jazz singer with a zest for life, and her stepson’s wife, Sophia, who challenges her to fight for independence. " +
                    "And though the many letters from Celie’s sister are hidden by her husband, Nettie’s unwavering support will prove to be the most breathtaking of all\n" +
                    "\n" +
                    "The Color Purple has sold more than five million copies, inspired an Academy Award-nominated film starring Whoopi Goldberg and Oprah Winfrey and directed by Steven Spielberg, and been adapted into a Tony-nominated Broadway musical. " +
                    "Lauded as a literary masterpiece, this is the groundbreaking novel that placed Walker “in the company of Faulkner” (The Nation) and remains a wrenching - yet intensely uplifting - experience for new generations of listeners.",
            coverImage = "https://i.pinimg.com/564x/84/81/62/848162062b67e9cef29b6e8a31870a31.jpg",
            localCoverImagePath = R.drawable.the_color_purple,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        ),
        Book(
            id =50,
            title = "The Silmarillion",
            author = "J.R.R. Tolkien",
            publicationYear = 1977,
            genres = listOf("Fantasy","Mythopoeia"),
            description = "A collection of mythopoeic stories about the history of Middle-earth.",
            longDescription = "The complete unabridged audiobook of J.R.R Tolkien's The Silmarillion.\n" +
                    "\n" +
                    "The Silmarillion is an account of the Elder Days, of the First Age of Tolkien’s world. " +
                    "It is the ancient drama to which the characters in The Lord of the Rings look back, and in whose events some of them such as Elrond and Galadriel took part. " +
                    "The tales of The Silmarillion are set in an age when Morgoth, the first Dark Lord, dwelt in Middle-Earth, and the High Elves made war upon him for the recovery of the Silmarils, the jewels containing the pure light of Valinor.\n" +
                    "\n" +
                    "Included in the book are several shorter works. " +
                    "'The Ainulindale' is a myth of the Creation and in the Valaquenta the nature and powers of each of the gods is described. " +
                    "'The Akallabeth' recounts the downfall of the great island kingdom of Númenor at the end of the Second Age and 'Of the Rings of Power' tells of the great events at the end of the Third Age, as narrated in The Lord of the Rings.",
            coverImage = "https://i.pinimg.com/736x/30/d7/bf/30d7bf3917404ddc6737de8691ea13e2.jpg",
            localCoverImagePath = R.drawable.the_silmarillion,
            price = Random.nextInt(10, 50),
            rates = Random.nextInt(1, 5)
        )
    )
}