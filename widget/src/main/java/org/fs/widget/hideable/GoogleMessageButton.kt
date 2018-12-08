/*
 * Google Message Widget Android Kotlin Copyright (C) 2018 Fatih.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fs.widget.hideable

import android.animation.ValueAnimator
import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.ViewCompat
import android.support.v4.widget.TextViewCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_layout_button.view.*

class GoogleMessageButton @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  style: Int = 0): LinearLayout(context, attrs, style), CoordinatorLayout.AttachedBehavior, View.OnLayoutChangeListener {

  private companion object {
    const val DURATION = 300L
    const val STATE_IDLE = 0
    const val STATE_UP = 1
    const val STATE_DOWN = 2
  }

  private var textWidth: Int = 0
  private val widthEvaluator by lazy { WidthEvaluator(viewTextButton) }

  private var state = STATE_IDLE

  init {
    View.inflate(context, R.layout.view_layout_button, this)
    val padding = resources.getDimensionPixelSize(R.dimen.button_margin) / 2
    val background = ResourcesCompat.getDrawable(resources, R.drawable.view_layout_button_bg, context.theme)
    ViewCompat.setBackground(this, background)
    ViewCompat.setElevation(this, resources.getDimension(R.dimen.button_elevation))
    ViewCompat.setPaddingRelative(this, padding, padding, padding, padding)
    // read component values from here
    context.obtainStyledAttributes(attrs, R.styleable.GoogleMessageButton).apply {
      val textAppearance = getResourceId(R.styleable.GoogleMessageButton_googleMessageButton_textAppearance, -1)
      if (textAppearance != -1) {
        TextViewCompat.setTextAppearance(viewTextButton, textAppearance)
      }
      val drawableRes = getResourceId(R.styleable.GoogleMessageButton_googleMessageButton_src, -1)
      if (drawableRes != -1) {
        viewImageButton.setImageResource(drawableRes)
      }
      val textSize = getDimension(R.styleable.GoogleMessageButton_googleMessageButton_textSize, -1f)
      if (textSize != -1f) {
        viewTextButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
      }
      val textColor = getResourceId(R.styleable.GoogleMessageButton_googleMessageButton_textColor, -1)
      if (textColor != -1) {
        viewTextButton.setTextColor(ResourcesCompat.getColor(resources, textColor, context.theme))
      }
      val text = getString(R.styleable.GoogleMessageButton_googleMessageButton_text)
      viewTextButton.text = text
      recycle()
    }
  }

  override fun getBehavior(): CoordinatorLayout.Behavior<*> = GoogleMessageButtonBehaviour()

  override fun onLayoutChange(v: View?, l: Int, t: Int, r: Int, b: Int, ol: Int, ot: Int, or: Int, ob: Int) {
    // try to get with
    textWidth = viewTextButton.width
    // remove this from layout
    removeOnLayoutChangeListener(this)
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    addOnLayoutChangeListener(this)
  }

  fun translate(dy: Int) = when {
    dy < 0 -> animateScrollDown()
    dy > 0 -> animateScrollUp()
    else -> Unit
  }

  private fun animateScrollUp() {
    if (state != STATE_UP) {
      clearAnimation()
      val from = if (viewTextButton.layoutParams.width != 0) textWidth else viewTextButton.layoutParams.width
      createAnimation(from, 0).start()
      state = STATE_UP
    }
  }

  private fun animateScrollDown() {
    if (state != STATE_DOWN) {
      clearAnimation()
      createAnimation(viewTextButton.layoutParams.width, textWidth).start()
      state = STATE_DOWN
    }
  }

  private fun createAnimation(from: Int, to: Int): ValueAnimator = ValueAnimator.ofObject(widthEvaluator, from, to).apply {
    duration = DURATION
    val listener = if (from > to) {
      AnimatorListener(start = {
        // viewTextButton.alpha = 0f
      }, end = {
        viewTextButton.visibility = View.GONE
      })
    } else {
      AnimatorListener(start = {
        viewTextButton.visibility = View.VISIBLE
        // viewTextButton.alpha = 0f
      }, end = {
        // viewTextButton.alpha = 1f
      })
    }
    addListener(listener)
  }
}