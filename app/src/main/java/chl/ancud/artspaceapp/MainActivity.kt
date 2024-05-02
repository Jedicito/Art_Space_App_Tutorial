package chl.ancud.artspaceapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chl.ancud.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Principal()
                }
            }
        }
    }
}

@Composable
fun Principal( modifier: Modifier = Modifier) {
    val imagenes: List<ImagenesConNombreyAutor> = listOf(
        ImagenesConNombreyAutor(
            imagenId = R.drawable.archivo1,
            nombre = "Imagen 1",
            autor = "Autor 1"),
        ImagenesConNombreyAutor(
            imagenId = R.drawable.archivo2,
            nombre = "Imagen 2",
            autor = "Autor 2"),
        ImagenesConNombreyAutor(
            imagenId = R.drawable.archivo3,
            nombre = "Imagen 3",
            autor = "Autor 3"),
        ImagenesConNombreyAutor(
            imagenId = R.drawable.archivo4,
            nombre = "Imagen 4",
            autor = "Autor 4"),
        ImagenesConNombreyAutor(
            imagenId = R.drawable.archivo5,
            nombre = "Imagen 5",
            autor = "Autor 5")
    )

    var posicionImagenActual by remember { mutableIntStateOf(0)}


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Cuadro_Foto(imagenes, posicionImagenActual)
        Spacer(modifier = Modifier.height(50.dp))
        Cuadro_Nombre_Autor(imagenes[posicionImagenActual].nombre, imagenes[posicionImagenActual].autor, modifier = Modifier)
        Spacer(modifier = Modifier.height(50.dp))
        Botones (
            posicionImagenActual = posicionImagenActual,
            posicionImagenMaxima = imagenes.size -1,
            posicionImagenAnterior = { posicionImagen -> posicionImagenActual = posicionImagen } ,
            posicionImagenSiguiente = { posicionImagen -> posicionImagenActual = posicionImagen })
        Log.d("posicionImagenActual", "posicionImagenActual $posicionImagenActual")
    }
}

@Composable
fun Cuadro_Foto(
    imagenes: List<ImagenesConNombreyAutor>,
    posicionImagenActual: Int,
    modifier: Modifier = Modifier
        .shadow(shape = RectangleShape, elevation = 3.dp, clip = true)
        .padding(28.dp)

) {

    val imagenActual = imagenes[posicionImagenActual]
    Image(
        painter = painterResource(id = imagenActual.imagenId),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun Cuadro_Nombre_Autor(
    nombre: String,
    autor: String,
    modifier: Modifier

) {
    Column (
        modifier = Modifier
            .background(Color(0xFFecebf4))
            .padding(10.dp)
    ) {
        Text(text = nombre,
            color = Color(0xFF515156),
            )
        Text(text = autor,
            color = Color(0xFF515156),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Botones(
    modifier: Modifier = Modifier,
    posicionImagenActual: Int,
    posicionImagenMaxima: Int,
    posicionImagenAnterior: (Int) -> Unit,
    posicionImagenSiguiente: (Int) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = {
                posicionImagenAnterior (
                    calc_posicionImagenAnterior(posicionImagenActual, posicionImagenMaxima)
                )
            Log.d("Boton Anterior", "Boton Anterior $posicionImagenActual")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF495d92))
        ) {
            Text(text = "Anterior")
        }
        Button(
            onClick = {
                posicionImagenSiguiente(
                    calc_posicionImagenSiguiente(posicionImagenActual, posicionImagenMaxima)
            )
                Log.d("Boton Siguiente", "Boton Siguiente $posicionImagenActual")
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF495d92))
        ) {
            Text(text = "Siguiente")
        }
    }
}

fun calc_posicionImagenSiguiente(posicion: Int, posicionMaxima: Int): Int {
    var pos: Int = posicion
    return if (posicion == posicionMaxima) {
        0
    } else {
        ++pos
    }
}
fun calc_posicionImagenAnterior(posicion: Int, posicionMaxima: Int): Int {
    var pos: Int = posicion
    return if (posicion == 0) {
        posicionMaxima
    } else {
        --pos
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        Principal()
    }
}

data class ImagenesConNombreyAutor(
    val imagenId: Int,
    val nombre: String,
    val autor: String
)