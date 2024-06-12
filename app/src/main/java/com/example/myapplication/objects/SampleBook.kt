package com.example.myapplication.objects

import com.example.myapplication.R
import com.example.myapplication.classes.Book
import kotlin.random.Random

object SampleBook {
//    private val jsonFile: File

//    init {
//        val jsonFile = File("res/raw/books.json")
//        val jsonString = jsonFile.readText();
//        val gson = Gson()
//        val book: Book = gson.fromJson(jsonString, Book::class.java);
//        println("User ID: ${book.getId()}")
//        Log.d("User ID",book.getId().toString())
//    }
//    private val bookOne = Book(
//        id = 43,
//        title = "The Metamorphosis",
//        author = "Franz Kafka",
//        publicationYear = 1915,
//        genres = arrayOf("Absurdist Fiction", "Existential"),
//        description = "A novella about a man who wakes up one morning transformed into a giant insect.",
//        coverImage = "https://m.media-amazon.com/images/W/MEDIAX_849526-T1/images/I/81QOkf8RSIL._SY522_.jpg"
//    )

    val books = listOf<Book>(
        Book(
            id = 1,
            title ="To Kill a Mockingbird",
            author = "Harper Lee",
            publicationYear = 1960,
            genres = arrayOf("Fiction", "Classic"),
            description = "A classic novel depicting racial injustice in the American South.",
            coverImage = "https://i.pinimg.com/564x/b3/b6/5b/b3b65b82c7820c5cbc15a5fef871d68d.jpg",
            localCoverImagePath = R.drawable.to_kill_a_mockingbird,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 2,
            title = "1984",
            author = "George Orwell",
            publicationYear = 1949,
            genres = arrayOf("Dystopian","Science Fiction"),
            description = "A dystopian novel portraying a totalitarian society.",
            coverImage = "https://i.pinimg.com/564x/04/44/6c/04446c92480b149be2e02f7a4f520922.jpg",
            localCoverImagePath = R.drawable.nineteeneightyfour,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 3,
            title = "Pride and Prejudice",
            author = "Jane Austen",
            publicationYear = 1813,
            genres = arrayOf("Classic","Romance"),
            description = "A classic novel exploring themes of love, marriage, and social norms.",
            coverImage = "https://i.pinimg.com/564x/5c/12/6e/5c126ebc616400330587845a172c110d.jpg",
            localCoverImagePath = R.drawable.pride_and_prejudice,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 4,
            title = "The Great Gatsby",
            author = "F. Scott Fitzgerald",
            publicationYear = 1925,
            genres = arrayOf("Fiction", "Classic"),
            description = "A tale of the American Dream, wealth, and love during the Roaring Twenties.",
            coverImage = "https://i.pinimg.com/736x/11/91/8f/11918f3c356159ed09856eb1664f9bff.jpg",
            localCoverImagePath = R.drawable.the_great_gatsby,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 5,
            title = "Moby-Dick",
            author = "Herman Melville",
            publicationYear = 1851,
            genres = arrayOf("Fiction", "Adventure"),
            description = "The epic tale of Captain Ahab's obsession with the white whale.",
            coverImage = "https://i.pinimg.com/564x/7a/49/68/7a49682a4ddac9cefb030be8db35a6af.jpg",
            localCoverImagePath = R.drawable.moby_dick,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 6,
            title = "The Lord of the Rings",
            author = "J.R.R. Tolkien",
            publicationYear = 1954,
            genres = arrayOf("Fantasy","Adventure"),
            description = "An epic fantasy saga about the quest to destroy the One Ring.",
            coverImage = "https://i.pinimg.com/564x/7b/6d/38/7b6d3896048f20a865b744ab69aaf216.jpg",
            localCoverImagePath = R.drawable.the_lord_of_the_rings,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 7,
            title = "The Catcher in the Rye",
            author = "J.D. Salinger",
            publicationYear = 1951,
            genres = arrayOf("Fiction","Coming-of-age"),
            description = "A classic coming-of-age novel following Holden Caulfield's journey.",
            coverImage = "https://i.pinimg.com/564x/4b/be/9c/4bbe9c695bd2c2005847108120df0612.jpg",
            localCoverImagePath = R.drawable.the_catcher_in_the_ray,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 8,
            title = "The Hobbit",
            author = "J.R.R. Tolkien",
            publicationYear = 1937,
            genres = arrayOf("Fantasy", "Adventure"),
            description = "The prequel to The Lord of the Rings, following Bilbo Baggins' journey.",
            coverImage = "https://i.pinimg.com/564x/fb/b0/74/fbb074b6a0674cb137b39d2b2acaf467.jpg",
            localCoverImagePath = R.drawable.the_hobit,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 9,
            title = "One Hundred Years of Solitude",
            author = "Gabriel Garcia Marquez",
            publicationYear = 1967,
            genres = arrayOf("Magical Realism", "Literary Fiction"),
            description = "A multi-generational saga of the Buendía family in the fictional town of Macondo.",
            coverImage = "https://i.pinimg.com/564x/fb/6c/57/fb6c57be3005b5759370732f9831ecb0.jpg",
            localCoverImagePath = R.drawable.one_hundred_years_of_solitude,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 10,
            title = "War and Peace",
            author = "Leo Tolstoy",
            publicationYear = 1869,
            genres = arrayOf("Historical Fiction", "Epic"),
            description = "A monumental work depicting the events of Russian society during the Napoleonic era.",
            coverImage = "https://i.pinimg.com/736x/70/15/41/70154164a2967cc25fbac089e855db38.jpg",
            localCoverImagePath = R.drawable.war_and_peace,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 11,
            title = "The Odyssey",
            author = "Homer",
            publicationYear = 700,
            genres = arrayOf("Epic","Mythology"),
            description = "An ancient Greek epic poem recounting Odysseus' ten-year journey home after the Trojan War.",
            coverImage = "https://i.pinimg.com/564x/38/1a/4a/381a4abdc63125efd1e31f0cf27571a0.jpg",
            localCoverImagePath = R.drawable.the_oddysey,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 12,
            title = "The Divine Comedy",
            author = "Dante Alighieri",
            publicationYear = 1320,
            genres =arrayOf("Epic", "Poetry"),
            description = "An epic poem that follows the journey of the soul through Hell, Purgatory, and Heaven.",
            coverImage = "https://i.pinimg.com/564x/6f/11/5a/6f115a218b2a30a66c252414a693529b.jpg",
            localCoverImagePath = R.drawable.the_divine_comedy,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 13,
            title = "The Brothers Karamazov",
            author = "Fyodor Dostoevsky",
            publicationYear = 1880,
            genres = arrayOf("Classic","Philosophical Fiction"),
            description = "A complex novel exploring themes of spirituality, morality, and human nature.",
            coverImage = "https://i.pinimg.com/564x/d5/69/3b/d5693b4b5cdaf0c97e706c098ebe828e.jpg",
            localCoverImagePath = R.drawable.the_brothers_karamazov,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 14,
            title = "Crime and Punishment",
            author = "Fyodor Dostoevsky",
            publicationYear = 1866,
            genres = arrayOf("Classic", "Psychological Fiction"),
            description = "A psychological thriller revolving around guilt, conscience, and redemption.",
            coverImage = "https://i.pinimg.com/564x/7b/5f/06/7b5f06ac3e549659269ff9fc478444dc.jpg",
            localCoverImagePath = R.drawable.crime_and_punishment,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 15,
            title = "The Picture of Dorian Gray",
            author = "Oscar Wilde",
            publicationYear = 1890,
            genres = arrayOf("Gothic", "Philosophical Fiction"),
            description = "A novel about a man whose portrait ages while he retains his youth and beauty.",
            coverImage = "https://i.pinimg.com/564x/7a/6e/fe/7a6efe7338bc9f61c790d0a7b673464b.jpg",
            localCoverImagePath = R.drawable.the_picture_of_dorian_gray,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 16,
            title = "Brave New World",
            author = "Aldous Huxley",
            publicationYear = 1932,
            genres = arrayOf("Dystopian","Science Fiction"),
            description = "A dystopian vision of a future society obsessed with pleasure and conformity.",
            coverImage = "https://i.pinimg.com/564x/5f/06/65/5f066515b64613faed22947a8029f139.jpg",
            localCoverImagePath = R.drawable.brave_new_world,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 17,
            title = "The Count of Monte Cristo",
            author = "Alexandre Dumas",
            publicationYear = 1844,
            genres = arrayOf("Adventure", "Historical Fiction"),
            description = "An adventure novel of revenge, love, and redemption set in the early 19th century.",
            coverImage = "https://i.pinimg.com/564x/76/f7/41/76f7410361344a0e15d56d8548530394.jpg",
            localCoverImagePath = R.drawable.the_count_of_monte_cristo,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 18,
            title = "Anna Karenina",
            author = "Leo Tolstoy",
            publicationYear = 1877,
            genres = arrayOf("Classic", "Romance"),
            description = "A tragic love story set against the backdrop of Russian high society.",
            coverImage = "https://i.pinimg.com/564x/6e/88/cc/6e88ccf11ae57c70afab36f858745a7f.jpg",
            localCoverImagePath = R.drawable.anna_karenina,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 19,
            title = "The Alchemist",
            author = "Paulo Coelho",
            publicationYear = 1988,
            genres = arrayOf("Fiction", "Philosophical"),
            description = "A philosophical novel about a shepherd boy's journey to find his personal legend.",
            coverImage = "https://i.pinimg.com/736x/98/bc/0a/98bc0a1a761bc9242f71e2e71f89dd7f.jpg",
            localCoverImagePath = R.drawable.the_alchemist,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 20,
            title = "The Adventures of Huckleberry Finn",
            author = "Mark Twain",
            publicationYear = 1884,
            genres = arrayOf("Adventure", "Satire"),
            description = "A satirical novel following Huck Finn's journey down the Mississippi River.",
            coverImage = "https://i.pinimg.com/564x/82/bc/93/82bc931dfe340d06c4b6ca3efdbbe4bd.jpg",
            localCoverImagePath = R.drawable.the_adventures_of_huckleberry_finn,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 21,
            title = "The Iliad",
            author = "Homer",
            publicationYear = -700,
            genres = arrayOf("Epic","Mythology"),
            description = "An ancient Greek epic poem about the Trojan War and the hero Achilles.",
            coverImage = "https://i.pinimg.com/564x/ce/6d/7c/ce6d7c97763fd6f07980fdae8485ce89.jpg",
            localCoverImagePath = R.drawable.the_iliad,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 22,
            title = "Leviathan",
            author = "Scott Westerfeld",
            publicationYear = 1651,
            genres =arrayOf("Fantasy","Adventure"),
            description = "A war story.",
            coverImage = "https://i.pinimg.com/564x/8b/c5/d0/8bc5d08aa1ba3d0cf470a322f5d742ad.jpg",
            localCoverImagePath = R.drawable.leviathan,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 23,
            title = "Don Quixote",
            author = "Miguel de Cervantes",
            publicationYear = 1605,
            genres = arrayOf("Classic", "Satire"),
            description = "A satirical novel about a deluded knight and his faithful squire, Sancho Panza.",
            coverImage = "https://i.pinimg.com/564x/d0/d7/8e/d0d78e71834a094a4b6527539c49faa3.jpg",
            localCoverImagePath = R.drawable.don_quixote,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 24,
            title = "Frankenstein",
            author = "Mary Shelley",
            publicationYear = 1818,
            genres = arrayOf("Gothic", "Science Fiction"),
            description = "A novel about the creation of a monster and the consequences of playing god.",
            coverImage = "https://i.pinimg.com/564x/94/37/81/9437811edf3de19193848c78a576a4a9.jpg",
            localCoverImagePath = R.drawable.frankenstein,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 25,
            title = "Alice's Adventures in Wonderland",
            author = "Lewis Carroll",
            publicationYear = 1865,
            genres = arrayOf("Fantasy", "Children's Literature"),
            description = "A whimsical tale about a girl named Alice who falls into a magical world.",
            coverImage = "https://i.pinimg.com/564x/fa/a5/9c/faa59c3617b6f821f48f40b67f653620.jpg",
            localCoverImagePath = R.drawable.alice_s_adventures_in_wonderland,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 26,
            title = "The Little Prince",
            author = "Antoine de Saint-Exupéry",
            publicationYear = 1943,
            genres = arrayOf("Fable","Children's Literature"),
            description = "A philosophical novella about a young prince's journey through the universe.",
            coverImage = "https://i.pinimg.com/564x/48/9a/af/489aaf3d176759902217b89899162a2b.jpg",
            localCoverImagePath = R.drawable.the_little_prince,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 27,
            title = "The Book Thief",
            author = "Markus Zusak",
            publicationYear = 2005,
            genres = arrayOf("Historical Fiction", "War"),
            description = "A story of a girl living in Nazi Germany, narrated by Death.",
            coverImage = "https://i.pinimg.com/564x/a9/c0/a4/a9c0a4a4c7f78cda7bbdc974149e6155.jpg",
            localCoverImagePath = R.drawable.the_book_thief,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 28,
            title = "Slaughterhouse-Five",
            author = "Kurt Vonnegut",
            publicationYear = 1969,
            genres = arrayOf("Satire", "Science Fiction"),
            description = "An anti-war novel that mixes science fiction and dark humor.",
            coverImage = "https://i.pinimg.com/564x/e9/6b/cd/e96bcdaffaa4211329d16a8463286c1e.jpg",
            localCoverImagePath = R.drawable.slaughterhouse_five,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 29,
            title = "The Grapes of Wrath",
            author = "John Steinbeck",
            publicationYear = 1939,
            genres = arrayOf("Historical Fiction", "Social Commentary"),
            description = "A novel about the plight of migrant workers during the Great Depression.",
            coverImage = "https://i.pinimg.com/564x/11/7a/5a/117a5a23afa8785306fd1aa67b7d1386.jpg",
            localCoverImagePath = R.drawable.the_grapes_of_wrath,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 30,
            title = "Fahrenheit 451",
            author = "Ray Bradbury",
            publicationYear = 1953,
            genres = arrayOf("Dystopian","Science Fiction"),
            description = "A dystopian novel depicting a future society where books are banned.",
            coverImage = "https://i.pinimg.com/564x/7b/63/3d/7b633dfb1ac4ff8dd2b47db80eeecf58.jpg",
            localCoverImagePath = R.drawable.fahrenheit_451,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 31,
            title = "The Lord of the Flies",
            author = "William Golding",
            publicationYear = 1954,
            genres = arrayOf("Dystopian","Psychological Fiction"),
            description = "A novel about a group of British boys stranded on an uninhabited island.",
            coverImage = "https://i.pinimg.com/564x/b5/78/e0/b578e094fcdee9bc43e9e10bf783e71c.jpg",
            localCoverImagePath = R.drawable.the_lor_of_the_files,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 32,
            title = "The Hitchhiker's Guide to the Galaxy",
            author = "Douglas Adams",
            publicationYear = 1979,
            genres = arrayOf("Science Fiction", "Comedy"),
            description = "A comedic science fiction series about the misadventures of Arthur Dent.",
            coverImage = "https://i.pinimg.com/564x/af/65/af/af65afc94ecd442c25d93a4dcd86d1f1.jpg",
            localCoverImagePath = R.drawable.the_hitchhiker_s_guide_to_the_galaxy,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 33,
            title = "A Tale of Two Cities",
            author = "Charles Dickens",
            publicationYear = 1859,
            genres = arrayOf("Historical Fiction","Classic"),
            description = "A historical novel set during the French Revolution, exploring themes of sacrifice and resurrection.",
            coverImage = "https://i.pinimg.com/564x/6b/d7/b6/6bd7b60745a1427d989819cf7a1fa52f.jpg",
            localCoverImagePath = R.drawable.a_tale_of_two_cities,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 34,
            title = "The Chronicles of Narnia",
            author = "C.S. Lewis",
            publicationYear = 1950,
            genres = arrayOf("Fantasy","Children's Literature"),
            description = "A series of fantasy novels set in the magical land of Narnia.",
            coverImage = "https://i.pinimg.com/564x/aa/eb/0a/aaeb0ae993357d7ffba9aa5cbcf8a04b.jpg",
            localCoverImagePath = R.drawable.the_chronicles_of_narnia,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 35,
            title = "The Handmaid's Tale",
            author = "Margaret Atwood",
            publicationYear = 1985,
            genres = arrayOf("Dystopian","Feminist Fiction"),
            description = "A dystopian novel set in a totalitarian society where women are subjugated.",
            coverImage = "https://i.pinimg.com/564x/7a/fe/33/7afe33fbafd769cdc7f9d32c329681d3.jpg",
            localCoverImagePath = R.drawable.the_handmaid_s_tale,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 36,
            title = "The Name of the Rose",
            author = "Umberto Eco",
            publicationYear = 1980,
            genres = arrayOf("Historical Fiction","Mystery"),
            description = "A medieval mystery novel set in an Italian monastery.",
            coverImage = "https://i.pinimg.com/564x/46/74/1f/46741fa4d7b21e5157a2444cd7a875d1.jpg",
            localCoverImagePath = R.drawable.the_name_of_the_rose,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 37,
            title = "The Trial",
            author = "Franz Kafka",
            publicationYear = 1925,
            genres = arrayOf("Absurdist Fiction","Existential"),
            description = "A surreal novel exploring themes of guilt, law, and justice.",
            coverImage = "https://i.pinimg.com/564x/19/96/c7/1996c72b66915b98e4585dc32e2a7a79.jpg",
            localCoverImagePath = R.drawable.the_trial,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 38,
            title = "The Kite Runner",
            author = "Khaled Hosseini",
            publicationYear = 2003,
            genres = arrayOf("Historical Fiction","Drama"),
            description = "A novel about friendship, redemption, and the impact of war in Afghanistan.",
            coverImage = "https://i.pinimg.com/564x/af/4f/8b/af4f8bc196aaff6f0c00b070660d1a19.jpg",
            localCoverImagePath = R.drawable.the_kite_runner,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 39,
            title = "The Pillars of the Earth",
            author = "Ken Follett",
            publicationYear = 1989,
            genres = arrayOf("Historical Fiction", "Adventure"),
            description = "An epic historical novel set in 12th-century England, centered around the construction of a cathedral.",
            coverImage = "https://i.pinimg.com/564x/67/d3/35/67d33537b5e081e3d7fcc82985f2c81f.jpg",
            localCoverImagePath = R.drawable.the_pillars_of_the_earth,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 40,
            title = "The Shadow of the Wind",
            author = "Carlos Ruiz Zafón",
            publicationYear = 2001,
            genres = arrayOf("Mystery","Gothic"),
            description = "A mystery novel set in post-war Barcelona, revolving around a forgotten book and its author.",
            coverImage = "https://i.pinimg.com/564x/99/6b/1a/996b1a484ca909f8f92f71451f8913f7.jpg",
            localCoverImagePath = R.drawable.the_shadow_of_the_wind,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 41,
            title = "The Secret Garden",
            author = "Frances Hodgson Burnett",
            publicationYear = 1911,
            genres = arrayOf("Children's Literature","Classic"),
            description = "A classic children's novel about a young girl who discovers a hidden garden.",
            coverImage = "https://i.pinimg.com/564x/53/f1/20/53f120f54a2ab1598c3f7d4d3782abec.jpg",
            localCoverImagePath = R.drawable.the_secret_garden,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 42,
            title = "The Giver",
            author = "Lois Lowry",
            publicationYear = 1993,
            genres = arrayOf("Dystopian","Young Adult"),
            description = "A dystopian novel about a society with strict control over emotions and memories.",
            coverImage = "https://i.pinimg.com/564x/3a/13/0e/3a130ef7ffe5be4eb73a87c1b1b85b92.jpg",
            localCoverImagePath = R.drawable.the_giver,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 43,
            title = "The Metamorphosis",
            author = "Franz Kafka",
            publicationYear = 1915,
            genres = arrayOf("Absurdist Fiction","Existential"),
            description = "A novella about a man who wakes up one morning transformed into a giant insect.",
            coverImage = "https://i.pinimg.com/564x/23/e9/93/23e99354e39803b73701b6cfaf8749fb.jpg",
            localCoverImagePath = R.drawable.the_metamorphosis,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 44,
            title = "Gone with the Wind",
            author = "Margaret Mitchell",
            publicationYear = 1936,
            genres = arrayOf("Historical Fiction","Romance"),
            description = "A historical novel set during the American Civil War, centered around Scarlett O'Hara.",
            coverImage = "https://i.pinimg.com/564x/1a/87/9a/1a879ac1fefdee0f1f8dd9c0afc9b60a.jpg",
            localCoverImagePath = R.drawable.gone_with_the_wind,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 45,
            title = "The Wind in the Willows",
            author = "Kenneth Grahame",
            publicationYear = 1908,
            genres = arrayOf("Children's Literature","Fantasy"),
            description = "A children's novel about the adventures of anthropomorphic animals.",
            coverImage = "https://i.pinimg.com/564x/81/38/0b/81380b23388651b772ee1a3b85ef896b.jpg",
            localCoverImagePath = R.drawable.the_wind_in_the_willows,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 46,
            title = "Dracula",
            author = "Bram Stoker",
            publicationYear = 1897,
            genres = arrayOf("Gothic","Horror"),
            description = "A Gothic horror novel about the vampire Count Dracula's attempt to move to England.",
            coverImage = "https://i.pinimg.com/564x/55/1f/7e/551f7e09a6793a8bd29df7e4759b508c.jpg",
            localCoverImagePath = R.drawable.dracula,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 47,
            title = "The Call of the Wild",
            author = "Jack London",
            publicationYear = 1903,
            genres = arrayOf("Adventure","Nature"),
            description = "An adventure novel about a domestic dog's life in the wilds of the Yukon.",
            coverImage = "https://i.pinimg.com/564x/5f/99/6c/5f996cd2f6d632c1039f586f3d8d1d6d.jpg",
            localCoverImagePath = R.drawable.the_call_of_the_wild,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 48,
            title = "The Stand",
            author = "Stephen King",
            publicationYear = 1978,
            genres = arrayOf("Horror","Post-Apocalyptic"),
            description = "A post-apocalyptic horror novel about a deadly pandemic and its aftermath.",
            coverImage = "https://i.pinimg.com/564x/e0/bf/72/e0bf72755d4211338e3b14a5b1791e0b.jpg",
            localCoverImagePath = R.drawable.the_stand,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id = 49,
            title = "The Color Purple",
            author = "Alice Walker",
            publicationYear = 1982,
            genres = arrayOf("Fiction","Historical"),
            description = "A novel about the life of African-American women in the Southern United States.",
            coverImage = "https://i.pinimg.com/564x/84/81/62/848162062b67e9cef29b6e8a31870a31.jpg",
            localCoverImagePath = R.drawable.the_color_purple,
            price = Random.nextInt(10, 50)
        ),
        Book(
            id =50,
            title = "The Silmarillion",
            author = "J.R.R. Tolkien",
            publicationYear = 1977,
            genres = arrayOf("Fantasy","Mythopoeia"),
            description = "A collection of mythopoeic stories about the history of Middle-earth.",
            coverImage = "https://i.pinimg.com/736x/30/d7/bf/30d7bf3917404ddc6737de8691ea13e2.jpg",
            localCoverImagePath = R.drawable.the_silmarillion,
            price = Random.nextInt(10, 50)
        )
    )
}