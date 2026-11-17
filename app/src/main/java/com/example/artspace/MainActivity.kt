package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                ArtSpaceApp()
            }
        }
    }
}

data class Artwork(
    val image: Int,
    val title: String,
    val artist: String,
    val description: String
)

@Composable
fun ArtSpaceApp() {

    val artworks = listOf(
        Artwork(
            R.drawable.staryy_night,
            "The Starry Night",
            "Vincent van Gogh (1889)",
            "Dilukis Van Gogh dari jendela kamarnya di rumah sakit jiwa Saint-Rémy-de-Provence. " +
                    "Langit malam yang berpusar, bintang-bintang bercahaya, dan pohon cemara gelap " +
                    "menciptakan suasana penuh emosi dan gerak."
        ),
        Artwork(
            R.drawable.mona_lisa,
            "Mona Lisa",
            "Leonardo da Vinci (sekitar 1503–1519)",
            "Potret setengah badan yang terkenal dengan senyum misteriusnya. Leonardo memakai " +
                    "teknik sfumato, yaitu transisi warna yang sangat halus tanpa garis tegas. " +
                    "Lukisan ini kini tersimpan di Museum Louvre, Paris."
        ),
        Artwork(
            R.drawable.water_lilies,
            "Water Lilies",
            "Claude Monet (1906)",
            "Bagian dari seri sekitar 250 lukisan Monet yang terinspirasi kolam di kebunnya " +
                    "di Giverny. Pantulan langit, bunga teratai, dan sapuan kuas lembut khas " +
                    "Impresionisme menangkap perubahan cahaya di permukaan air."
        ),
        Artwork(
            R.drawable.great_wave,
            "The Great Wave off Kanagawa",
            "Katsushika Hokusai (sekitar 1831)",
            "Cetakan balok kayu ukiyo-e dari seri Thirty-Six Views of Mount Fuji. Ombak raksasa " +
                    "dengan puncak seperti cakar mengancam perahu nelayan, sementara Gunung Fuji " +
                    "tampak kecil dan tenang di kejauhan."
        ),
        Artwork(
            R.drawable.sunflowers,
            "Sunflowers",
            "Vincent van Gogh (1888)",
            "Salah satu seri vas bunga matahari yang dilukis Van Gogh di Arles. Dominasi warna " +
                    "kuning dan sapuan kuas tebal membuat bunga-bunga ini terasa hidup, dan lukisan " +
                    "ini dibuat untuk menghiasi kamar sahabatnya, Paul Gauguin."
        )
    )

    var currentIndex by remember {
        mutableIntStateOf(0)
    }

    val currentArtwork = artworks[currentIndex]

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFAF8FC)
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(1f))


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .shadow(8.dp),
                shape = RoundedCornerShape(2.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                Image(
                    painter = painterResource(
                        id = currentArtwork.image
                    ),
                    contentDescription = currentArtwork.title,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(18.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE5E1E8))
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = currentArtwork.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = currentArtwork.artist,
                    fontSize = 14.sp,
                    color = Color(0xFF555555)
                )

                Spacer(modifier = Modifier.height(10.dp))


                Text(
                    text = currentArtwork.description,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = Color(0xFF444444)
                )
            }

            Spacer(modifier = Modifier.weight(1f))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = {
                        currentIndex =
                            if (currentIndex == 0) {
                                artworks.lastIndex
                            } else {
                                currentIndex - 1
                            }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4C6199)
                    )
                ) {
                    Text(
                        text = "Previous",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                Button(
                    onClick = {
                        currentIndex =
                            (currentIndex + 1) % artworks.size
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4C6199)
                    )
                ) {
                    Text(
                        text = "Next",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    MaterialTheme {
        ArtSpaceApp()
    }
}