package org.fako.millitakim

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import millitakim.composeapp.generated.resources.Res
import millitakim.composeapp.generated.resources.arda
import millitakim.composeapp.generated.resources.baris
import millitakim.composeapp.generated.resources.ferdi
import millitakim.composeapp.generated.resources.guler
import millitakim.composeapp.generated.resources.hakan
import millitakim.composeapp.generated.resources.ismail
import millitakim.composeapp.generated.resources.kaan
import millitakim.composeapp.generated.resources.kenan
import millitakim.composeapp.generated.resources.kero
import millitakim.composeapp.generated.resources.merih
import millitakim.composeapp.generated.resources.salih
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
@Preview
fun App() {
    MaterialTheme {
                val navController = rememberNavController()
        NavHost(navController, startDestination = "main"){
            composable("main"){
                MainPage(items = list){user ->
                    navController.navigate("detail/${user.id}")
                }
            }
            composable("detail/{id}"){
                val id = it.arguments?.getString("id") ?:""
                DetailScreen(id.toInt()){
                    navController.navigateUp()
                }
            }
        }
    }
}

@Composable
fun MainPage(items:List<Player>, onItemClick: (Player) -> Unit) {

            LazyColumn(
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.background(Color.Red)
            ) {
                items(items, key = { list -> list.id}){user ->
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.padding(all = 8.dp).fillMaxWidth().clickable {
                            onItemClick(user)
                        }
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(user.imageId),
                                contentDescription = "",
                                modifier = Modifier.size(100.dp).padding(16.dp).clip(
                                    RoundedCornerShape(16.dp)
                                )
                            )

                            Column {
                                Text(text = user.name)
                                Text(text = user.team)
                            }
                        }
                    }

                }
            }
}

@Composable
fun DetailScreen(userId:Int, goItemBack:() -> Unit) {
    val players = list.first{it.id ==userId}
    val statistics = stringResource(players.statisticsId)

    Scaffold(
            topBar = {
                TopAppBar(
                    modifier = Modifier.windowInsetsPadding(WindowInsets(0.dp)),
                    title = {
                        Text(players.name, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    },
                    navigationIcon = {
                        IconButton(onClick = goItemBack){
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
                        }
                    }
                )
            }
        ) {
            Column(modifier = Modifier.padding(it).fillMaxSize().padding(16.dp)) {
                val painter = painterResource(players.imageId)
                Box(
                    modifier = Modifier.fillMaxWidth().size(700.dp,300.dp)
                ){
                    Image(
                        painter,
                        modifier = Modifier.fillMaxWidth().size(700.dp,300.dp).padding(8.dp),
                        contentDescription = players.name
                    )
                }
                Text(players.name)
                Spacer(modifier = Modifier.height(16.dp))
                Text(players.team)
                Spacer(modifier = Modifier.height(32.dp))
                Text(statistics)

            }
        }
}

data class Player(
    val id:Int, val name:String,
    val team:String,
    val imageId: DrawableResource,
     val statisticsId:StringResource)

    val playerName = listOf("Arda Güler","Hakan Çalhanoğlu","Barış Alper Yılmaz","Merih Demiral","Kenan Yıldız","Ferdi Kadıoğlu",
        "Kaan Ayhan","Kerem Aktürkoğlu","Salih Özcan","İsmail Yüksek")
    val teamName = listOf("Real Madrid","Inter Milan","Galatasaray","Al-Ahli SFC","Juventus","Fenerbahçe","Galatasaray","Galatasaray","Borussia Dortmund","Fenerbahçe")
    val imageResId = listOf(
        Res.drawable.guler,
        Res.drawable.hakan,
        Res.drawable.baris,
        Res.drawable.merih,
        Res.drawable.kenan,
        Res.drawable.ferdi,
        Res.drawable.kenan,
        Res.drawable.kero,
        Res.drawable.salih,
        Res.drawable.ismail
    )
    val statisticsId = listOf(
        Res.string.arda,
        Res.string.hakan,
        Res.string.baris,
        Res.string.merih,
        Res.string.kenan,
        Res.string.ferdi,
        Res.string.kaan,
        Res.string.kero,
        Res.string.salih,
        Res.string.ismail
    )


val list = List(10){index ->
    val imageRes = imageResId [index % imageResId.size]
    Player(index, playerName[index], teamName[index],imageRes, statisticsId[index])
}