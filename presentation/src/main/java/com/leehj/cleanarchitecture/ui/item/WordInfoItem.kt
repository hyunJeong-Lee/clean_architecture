package com.leehj.cleanarchitecture.ui.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leehj.cleanarchitecture.R
import com.leehj.cleanarchitecture.ui.theme.CleanArchitectureTheme
import com.leehj.domain.entity.Definition
import com.leehj.domain.entity.Meaning
import com.leehj.domain.entity.WordInfo

@Composable
fun WordInfoItemCardView(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {

        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = wordInfo.word,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            wordInfo.phonetic?.let { phonetic ->
                Text(
                    text = phonetic,
                    fontWeight = FontWeight.Light
                )
            }
            wordInfo.origin?.let{ origin ->
                Text(text = origin)

            }
            wordInfo.meanings?.forEach { meaning ->
                Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.orange))
                meaning.definitions.forEachIndexed{ index, definition ->
                    Text(text = "${index+1}. ${definition.definition}")
                    Spacer(modifier = Modifier.height(8.dp))
                    definition.example?.let{ example ->
                        Text(text = "Example : $example")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
@Preview
fun Preview(){
    CleanArchitectureTheme {
        WordInfoItemCardView(wordInfo = dummyDatas().get(0))
    }
}

fun dummyDatas(): List<WordInfo> {
    val wordInfoList = listOf(
        WordInfo(
            word = "test",
            phonetic = "test",
            origin = "",
            meanings = listOf(
                Meaning(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        Definition(
                            definition = "A challenge, trial.",
                            synonyms = emptyList(),
                            antonyms = emptyList(),
                            example = ""
                        ),
                        Definition(
                            definition = "A cupel or cupelling hearth in which precious metals are melted for trial and refinement.",
                            synonyms = emptyList(),
                            antonyms = emptyList(),
                            example = ""
                        ),
                        // ... (나머지 정의들)
                    ),
                ),
                Meaning(
                    partOfSpeech = "verb",
                    definitions = listOf(
                        Definition(
                            definition = "To challenge.",
                            synonyms = emptyList(),
                            antonyms = emptyList(),
                            example = "Climbing the mountain tested our stamina."
                        ),
                        // ... (나머지 정의들)
                    ),
                )
            )
        ),
        WordInfo(
            word = "test",
            phonetic = "/test/",
            origin = "",
            meanings = listOf(
                Meaning(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        Definition(
                            definition = "A witness.",
                            synonyms = emptyList(),
                            antonyms = emptyList(),
                            example = ""
                        )
                    ),
                ),
                Meaning(
                    partOfSpeech = "verb",
                    definitions = listOf(
                        Definition(
                            definition = "To attest (a document) legally, and date it.",
                            synonyms = emptyList(),
                            antonyms = emptyList(),
                            example = ""
                        ),
                        Definition(
                            definition = "To make a testament, or will.",
                            synonyms = emptyList(),
                            antonyms = emptyList(),
                            example = ""
                        )
                    ),
                )
            )
        ),
        WordInfo(
            word = "test",
            phonetic = "/test/",
            origin = "",
            meanings = listOf(
                Meaning(
                    partOfSpeech = "noun",
                    definitions = listOf(
                        Definition(
                            definition = "(body building) testosterone",
                            synonyms = emptyList(),
                            antonyms = emptyList(),
                            example = ""
                        )
                    ),
                )
            )
        )
    )
    return wordInfoList
}