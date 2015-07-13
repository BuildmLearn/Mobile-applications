package com.buildmlearn.labeldiagram.widget;

import com.example.labelthediagram.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.TextView;

/**
 * This class is for defining and generating custom fonts for
 * the entire application
 * 
 * @author Akilaz
 *
 */

public class CustomFontManager extends TextView {

	private final static int ROBOTO_THIN = 0;
	private final static int ROBOTO_LIGHT = 1;
	private final static int ROBOTO_REGULAR = 2;
	private final static int ROBOTO_BOLD = 3;
	private final static int ROBOTO_CONDENSED_LIGHT = 4;
	private final static int ROBOTO_CONDENSED_LIGHT_ITALIC = 5;
	private final static int ROBOTO_CONDENSED_REGULAR = 6;

	private final static SparseArray<Typeface> mTypefaces = new SparseArray<Typeface>(
			7);

	/**
	 * Simple constructor to use when creating a view from code.
	 *
	 * @param context
	 *            The Context the view is running in, through which it can
	 *            access the current theme, resources, etc.
	 */
	public CustomFontManager(Context context) {
		super(context);
	}

	/**
	 * @param context
	 *            The Context the view is running in, through which it can
	 *            access the current theme, resources, etc.
	 * @param attrs
	 *            The attributes of the XML tag that is inflating the view.
	 * @param defStyle
	 *            The default style to apply to this view. If 0, no style will
	 *            be applied (beyond what is included in the theme). This may
	 *            either be an attribute resource, whose value will be retrieved
	 *            from the current theme, or an explicit style resource.
	 */
	public CustomFontManager(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		parseAttributes(context, attrs);
	}

	/**
	 * @param context
	 *            The Context the view is running in, through which it can
	 *            access the current theme, resources, etc.
	 * @param attrs
	 *            The attributes of the XML tag that is inflating the view.
	 * @see #CustomFontManager(Context, AttributeSet, int)
	 *
	 */
	public CustomFontManager(Context context, AttributeSet attrs) {
		super(context, attrs);
		parseAttributes(context, attrs);
	}

	/**
	 * Parse the attributes.
	 *
	 * @param context
	 *            The Context the view is running in, through which it can
	 *            access the current theme, resources, etc.
	 * @param attrs
	 *            The attributes of the XML tag that is inflating the view.
	 */
	private void parseAttributes(Context context, AttributeSet attrs) {
		TypedArray values = context.obtainStyledAttributes(attrs,
				R.styleable.CustomFontManager);

		int typefaceValue = values.getInt(
				R.styleable.CustomFontManager_typeface, 0);
		values.recycle();

		setTypeface(obtaintTypeface(context, typefaceValue));
	}

	/**
	 * Obtain typeface.
	 * 
	 * @param context
	 *            The Context the view is running in, through which it can
	 *            access the current theme, resources, etc.
	 * @param typefaceValue
	 *            values for the typeface attribute
	 * @return Roboto {@link Typeface}
	 * @throws IllegalArgumentException
	 *             if unknown `typeface` attribute value
	 */
	private Typeface obtaintTypeface(Context context, int typefaceValue)
			throws IllegalArgumentException {
		Typeface typeface = mTypefaces.get(typefaceValue);
		if (typeface == null) {
			typeface = createTypeface(context, typefaceValue);
			mTypefaces.put(typefaceValue, typeface);
		}
		return typeface;
	}

	/**
	 * Create typeface from assets.
	 * 
	 * @param context
	 *            The Context the view is running in, through which it can
	 *            access the current theme, resources, etc.
	 * @param typefaceValue
	 *            values for the typeface attribute
	 * @return Roboto {@link Typeface}
	 * @throws IllegalArgumentException
	 *             if unknown `typeface` attribute value
	 */
	private Typeface createTypeface(Context context, int typefaceValue)
			throws IllegalArgumentException {
		Typeface typeface;
		switch (typefaceValue) {
		case ROBOTO_THIN:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-Thin.ttf");
			break;
		case ROBOTO_LIGHT:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-Light.ttf");
			break;
		case ROBOTO_REGULAR:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-Regular.ttf");
			break;
		case ROBOTO_BOLD:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/Roboto-Bold.ttf");
			break;
		case ROBOTO_CONDENSED_LIGHT:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/RobotoCondensed-Light.ttf");
			break;
		case ROBOTO_CONDENSED_LIGHT_ITALIC:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/RobotoCondensed-LightItalic.ttf");
			break;
		case ROBOTO_CONDENSED_REGULAR:
			typeface = Typeface.createFromAsset(context.getAssets(),
					"fonts/RobotoCondensed-Regular.ttf");
			break;
		default:
			throw new IllegalArgumentException(
					"Unknown `typeface` attribute value " + typefaceValue);
		}
		return typeface;
	}
}
