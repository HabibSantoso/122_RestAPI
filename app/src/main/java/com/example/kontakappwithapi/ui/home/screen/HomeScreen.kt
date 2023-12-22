package com.example.kontakappwithapi.ui.home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kontakappwithapi.R
import com.example.kontakappwithapi.model.Kontak
import com.example.kontakappwithapi.ui.home.viewmodel.KontakUIState

@Composable
fun HomeScreen(
    kontakUIState: KontakUIState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when(kontakUIState) {
        is KontakUIState.Loading -> OnLoading(modifier = Modifier.fillMaxSize())
        is KontakUIState.Success -> KontakLayout(
            kontak = kontakUIState.kontak, modifier = modifier.fillMaxSize())

        is KontakUIState.Error -> OnError(retryAction = retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(id = R.string.loading))
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = "")
        Text(text = stringResource(id = R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
fun KontakLayout(
    kontak: List<Kontak>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)){
        items(kontak){ kontak ->
            KontakCard(
                kontak = kontak,
                modifier = Modifier
                    .fillMaxWidth())
        }
    }
}

@Composable
fun KontakCard(
    kontak: Kontak,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ){
        Row (
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = kontak.nama,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = null,
            )
            Text(
                text = kontak.nohp,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}