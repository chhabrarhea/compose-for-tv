package com.rhea.composefortv.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.rhea.composefortv.R

@Composable
fun ErrorScreen(
  focusRequester: FocusRequester = FocusRequester(),
  onRetryClicked: () -> Unit = {}
) {
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Icon(
      painter = painterResource(id = R.drawable.outline_block_24),
      modifier = Modifier.size(100.dp).padding(16.dp),
      contentDescription = stringResource(id = R.string.error_icon)
    )
    Text(text = stringResource(id = R.string.error_message), style = MaterialTheme.typography.titleSmall)

    Button(onClick = onRetryClicked, Modifier.focusRequester(focusRequester).padding(16.dp)) {
      Text(text = stringResource(id = R.string.retry))
    }
  }
}