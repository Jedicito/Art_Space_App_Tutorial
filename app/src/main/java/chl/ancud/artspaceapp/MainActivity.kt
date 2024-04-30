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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Button
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
fun Principal(posicionImagen: Int = 0, modifier: Modifier = Modifier) {
    val imagenes: List<Int> = listOf(
        R.drawable.archivo1, R.drawable.archivo2, R.drawable.archivo3,
        R.drawable.archivo4, R.drawable.archivo5
    )
    var posicionImagenActual by remember { mutableIntStateOf(posicionImagen)}
    var imagen = imagenes[posicionImagenActual]


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Cuadro_Foto(imagen)
        Spacer(modifier = Modifier.height(50.dp))
        Cuadro_Nombre_Autor(posicionImagenActual)
        Spacer(modifier = Modifier.height(50.dp))
        Botones { posicionImagen -> posicionImagenActual = posicionImagen }
        Log.d("posicionImagenActual", "posicionImagenActual $posicionImagenActual")
    }
}

@Composable
fun Cuadro_Foto(imagen: Int) {
    Box (
        modifier = Modifier
            //.padding(15.dp)
            //.border(border = BorderStroke(width = 1.dp, Color.White))
            .shadow(
                elevation = 3.dp,
                shape = RectangleShape,
            )
            .offset(x = 3f.dp)
            .padding(15.dp)

    ) {
        Image(
            painter = painterResource(id = imagen),
            contentDescription = null,
        )
    }
}

@Composable
fun Cuadro_Nombre_Autor(posicionImagenActual: Int) {
    Column (
        modifier = Modifier
            .background(Color.LightGray)
            .padding(10.dp)
    ) {
        Text(text = "Nombre de la Foto")
        Text(text = "Autor de la foto")
    }
}

@Composable
fun Botones(posicionImagen: (Int) -> Unit) {

    Row {
        Button(

            onClick = {
                Log.d("onClick Siguiente", "posicionImagenActual_1 $posicionImagen")
                //val posicionImagenActual = posicionImagen
                Log.d("onClick Siguiente", "posicionImagenActual_2 $posicionImagenActual")
                posicionImagen.invoke(posicionImagen.invoke() + 1)
            }) {
            Text(text = "Siguiente")

        }
    }
}

fun posicionImagenSiguiente(posicion: Int): Int {
    var pos: Int = posicion
    return pos++
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