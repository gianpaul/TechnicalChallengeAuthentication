package exirium.pe.authflowapp.presentation.color.colors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import exirium.pe.authflowapp.ui.components.BlurredCard

@Composable
fun ColorListScreen(
    viewModel: ColorListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    ColorListContent(state) { viewModel.onEvent(it) }
}

@Composable
private fun ColorListContent(
    state: ColorListState,
    onEvent: (ColorListEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        Spacer(modifier = Modifier.weight(0.1f))
        Column(modifier = Modifier.weight(0.4f)) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "Color List",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.padding(8.dp))
            ColorListCard(state, onEvent)
        }
    }
}

@Composable
private fun ColorListCard(
    state: ColorListState,
    onEvent: (ColorListEvent) -> Unit
) {
    BlurredCard {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.colors) { colorData ->
                ColorItem(colorData)
            }
            item {
                LoadMoreButton(state, onEvent)
            }
        }
    }
}

@Composable
private fun LoadMoreButton(
    state: ColorListState,
    onEvent: (ColorListEvent) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.hasMore) {
            Button(onClick = { onEvent(ColorListEvent.LoadMore) }) {
                Text("Load More")
            }
        }
    }
}

@Composable
fun ColorItem(colorData: exirium.pe.authflowapp.domain.entity.Color) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color(android.graphics.Color.parseColor(colorData.color))
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                text = colorData.name,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White
            )
            Text(
                text = "Year: ${colorData.year}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Text(
                text = "Pantone: ${colorData.pantone}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ColorListScreenPreview() {
    ColorListScreen()
}