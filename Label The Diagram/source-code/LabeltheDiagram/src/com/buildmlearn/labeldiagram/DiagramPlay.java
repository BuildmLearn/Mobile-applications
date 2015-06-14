package com.buildmlearn.labeldiagram;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.buildmlearn.labeldiagram.helper.PlaceHolderContainer;
import com.buildmlearn.labeldiagram.helper.TagContainerSingleton;
import com.buildmlearn.labeldiagram.helper.TagPlaceholderMapper;
import com.buildmlearn.labeldiagram.tooltipkit.InfoTooltip;
import com.buildmlearn.labeldiagram.tooltipkit.CustomTooltip.AlignMode;
import com.example.labelthediagram.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class is for handle DiagramPlay activity of Human Eye diagram
 * 
 * @author Akilaz
 *
 */
public class DiagramPlay extends Activity implements OnDragListener,
		OnLongClickListener, OnClickListener {

	private static final String TAG = "InfoTag";
	private static final String TAG_ERROR = "Error";
	private List<Integer[]> placeHolderlist = new ArrayList<Integer[]>();
	private SparseIntArray tagPlaceHolderMap = new SparseIntArray();
	private List<TextView> correctTagList = new ArrayList<TextView>();
	private List<TextView> incorrectTagList = new ArrayList<TextView>();
	private int correctLabeledCount = 0;
	private int totalLabeledCount = 0;
	private int tagListSize = 0;
	private TextView compeleteRatio;
	private TextView score;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.diagram_play);

		// Enabling ActionBar
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Human Eye");
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.show();

		// Score board textViews
		TextView completeTxt = (TextView) findViewById(R.id.complatedTxt);
		compeleteRatio = (TextView) findViewById(R.id.complete_ratio);
		TextView scoreTxt = (TextView) findViewById(R.id.scoreTxt);
		score = (TextView) findViewById(R.id.score);

		Typeface tfThin = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Thin.ttf");
		Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");

		// Setting up font face to Roboto Light/Thin
		completeTxt.setTypeface(tfThin);
		scoreTxt.setTypeface(tfThin);
		compeleteRatio.setTypeface(tfThin);
		score.setTypeface(tfThin);

		// Placeholder imageViews
		ImageView irisView = (ImageView) findViewById(R.id.irisBlb);
		ImageView pupilView = (ImageView) findViewById(R.id.pupilBlb);
		ImageView lensView = (ImageView) findViewById(R.id.lensBlb);
		ImageView corneaView = (ImageView) findViewById(R.id.corneaBlb);
		ImageView vitreousView = (ImageView) findViewById(R.id.vitreousBlb);
		ImageView ciliaryView = (ImageView) findViewById(R.id.ciliaryBodyBlb);
		ImageView nerveView = (ImageView) findViewById(R.id.opticNerveBlb);
		ImageView blindspotView = (ImageView) findViewById(R.id.blindSpotBlb);
		ImageView foveaView = (ImageView) findViewById(R.id.foveaBlb);
		ImageView retinaView = (ImageView) findViewById(R.id.retinaBlb);

		// Draggable Tags imageViews
		TextView irisTag = (TextView) findViewById(R.id.irisTag);
		TextView pupilTag = (TextView) findViewById(R.id.pupilTag);
		TextView lensTag = (TextView) findViewById(R.id.lensTag);
		TextView corneaTag = (TextView) findViewById(R.id.corneaTag);
		TextView vitreousTag = (TextView) findViewById(R.id.vitreousTag);
		TextView ciliaryTag = (TextView) findViewById(R.id.ciliaryTag);
		TextView nerveTag = (TextView) findViewById(R.id.nerveTag);
		TextView blindspotTag = (TextView) findViewById(R.id.opticdiskTag);
		TextView foveaTag = (TextView) findViewById(R.id.foveaTag);
		TextView retinaTag = (TextView) findViewById(R.id.retinaTag);

		// Register draggable views to receive drag events
		irisView.setOnDragListener(this);
		pupilView.setOnDragListener(this);
		lensView.setOnDragListener(this);
		corneaView.setOnDragListener(this);
		vitreousView.setOnDragListener(this);
		ciliaryView.setOnDragListener(this);
		nerveView.setOnDragListener(this);
		blindspotView.setOnDragListener(this);
		foveaView.setOnDragListener(this);
		retinaView.setOnDragListener(this);

		// Register place holders to receive onLongclick events
		irisTag.setOnLongClickListener(this);
		pupilTag.setOnLongClickListener(this);
		lensTag.setOnLongClickListener(this);
		corneaTag.setOnLongClickListener(this);
		vitreousTag.setOnLongClickListener(this);
		ciliaryTag.setOnLongClickListener(this);
		nerveTag.setOnLongClickListener(this);
		blindspotTag.setOnLongClickListener(this);
		foveaTag.setOnLongClickListener(this);
		retinaTag.setOnLongClickListener(this);

		// Register place holders to receive onClick events
		irisTag.setOnClickListener(this);
		pupilTag.setOnClickListener(this);
		lensTag.setOnClickListener(this);
		corneaTag.setOnClickListener(this);
		vitreousTag.setOnClickListener(this);
		ciliaryTag.setOnClickListener(this);
		nerveTag.setOnClickListener(this);
		blindspotTag.setOnClickListener(this);
		foveaTag.setOnClickListener(this);
		retinaTag.setOnClickListener(this);

		PlaceHolderContainer container = new PlaceHolderContainer();
		placeHolderlist = container.diagramCaller("HumanEye");

		TagPlaceholderMapper tagPlaceholdermapper = new TagPlaceholderMapper();
		tagPlaceHolderMap = tagPlaceholdermapper.diagramMapper("HumanEye");

		tagListSize = tagPlaceHolderMap.size();
	}

	@Override
	public boolean onLongClick(View textview) {
		ClipData clipData = ClipData.newPlainText("", "");

		View.DragShadowBuilder shadowBuilder = new TagDragShadowBuilder(
				textview);

		// Start the drag - contains the data to be dragged, metadata for this
		// data and callback for drawing shadow

		textview.startDrag(clipData, shadowBuilder, textview, 0);
		// Dragging the shadow so make the view invisible
		textview.setVisibility(View.INVISIBLE);
		return true;
	}

	@Override
	public boolean onDrag(View droppableView, DragEvent event) {

		// Defines a variable to store the action type for the incoming event
		final int action = event.getAction();
		final View draggedImageTag = (View) event.getLocalState();

		// Handles each of the expected events
		switch (action) {

		case DragEvent.ACTION_DRAG_STARTED:

			// Determines if the View can accept the dragged data
			if (event.getClipDescription().hasMimeType(
					ClipDescription.MIMETYPE_TEXT_PLAIN)) {

				Log.i(TAG, "Can accept this data");

				// returns true to indicate that the View can accept the dragged
				// data.
				return true;

			} else {
				Log.i(TAG, "Can not accept this data");

			}

			return false;

		case DragEvent.ACTION_DRAG_ENTERED:

			Toast.makeText(getApplicationContext(), "Ready to drop !",
					Toast.LENGTH_SHORT).show();
			Log.i(TAG, "drag action entered");
			return true;

		case DragEvent.ACTION_DRAG_LOCATION:
			Log.i(TAG, "drag action location");
			// Ignore the event
			return true;

		case DragEvent.ACTION_DRAG_EXITED:
			Log.i(TAG, "drag action exited");
			// Calls when the Tag exited the place holder imageView
			return true;

		case DragEvent.ACTION_DROP:

			boolean side;
			Log.i(TAG, "drag action dropped");

			// Set place holder imageViews invisible
			droppableView.setVisibility(View.INVISIBLE);

			// Get the positioning side(within diagram) of the currently entered
			// place holder
			side = getPlaceHolderSide(droppableView);

			// If placeholder is on left side, set XY coordinates of the
			// draggedImageTag to right top corner
			if (side == true) {
				draggedImageTag
						.setX(droppableView.getX() + droppableView.getWidth()
								- draggedImageTag.getWidth());
				draggedImageTag.setY(droppableView.getY());
			}

			// If placeholder is on right side, set XY coordinates of the
			// draggedImageTag to left top corner
			else if (side == false) {
				draggedImageTag.setX(droppableView.getX());
				draggedImageTag.setY(droppableView.getY());
			}

			// Obtain the correct Tag id corresponding to place holder id
			int desiredTagId = tagPlaceHolderMap.get(droppableView.getId());

			// Get Tag id of currently moving tag
			int currentTagId = draggedImageTag.getId();

			if (currentTagId != desiredTagId) {

				incorrectTagList.add((TextView) draggedImageTag);

				// If currently moving tag id doesn't match actual tag id
				// change the tag color to RED
				draggedImageTag
						.setBackgroundResource(R.drawable.custom_textview_incorrect);

				totalLabeledCount += 1;

				// Update score and progress
				updateProgress(correctLabeledCount, totalLabeledCount);

			} else if (currentTagId == desiredTagId) {

				correctTagList.add((TextView) draggedImageTag);

				// If currently moving tag id does match, actual tag id change
				// the tag color to light greenS
				draggedImageTag
						.setBackgroundResource(R.drawable.custom_textview_correct);

				totalLabeledCount += 1;
				correctLabeledCount += 1;

				// Update score and progress
				updateProgress(correctLabeledCount, totalLabeledCount);
			}

			draggedImageTag.setVisibility(View.VISIBLE);
			return true;

		case DragEvent.ACTION_DRAG_ENDED:

			Log.i(TAG, "getResult: " + event.getResult());

			// if the drop was not successful, set the tag to original place
			if (!event.getResult()) {
				draggedImageTag.post(new Runnable() {

					@Override
					public void run() {

						Log.i(TAG, "setting visible");
						draggedImageTag.setVisibility(View.VISIBLE);

					}
				});
			}

			return true;

			// An unknown action type was received.
		default:
			Log.e("DragDrop Example",
					"Unknown action type received by OnDragListener.");
			break;
		}

		return true;

	}

	/**
	 * Update score and progress
	 * 
	 * @param correcTries
	 * @param totalTries
	 */
	private void updateProgress(int correcTries, int totalTries) {

		final float progress;
		final float totalScore;
		final int MAX_PROGRESS = 100;

		totalScore = (float) correcTries / tagListSize * 100;
		progress = (float) totalTries / tagListSize * 100;

		score.setText((int) totalScore + "%");
		compeleteRatio.setText((int) progress + "%");

		if ((int) progress == MAX_PROGRESS) {

			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {

					// Initialize singleton class
					TagContainerSingleton container = TagContainerSingleton
							.getInstance();

					// set correct and incorrect tag lists
					container.setCorrectLabelList(correctTagList);
					container.setIncorrectLabelList(incorrectTagList);

					Intent intent = new Intent(getApplicationContext(),
							DiagramResult.class);
					intent.putExtra("SCORE", totalScore);
					startActivity(intent);

				}
			}, 3000);

		}

	}

	/**
	 * Indicate whether the placeholder is on left side or right side of the
	 * diagram
	 * 
	 * @param droppableView
	 * @return
	 */
	private boolean getPlaceHolderSide(View droppableView) {

		boolean isOnLeftSide = false;

		// Check whether the place holder(droppableView) is in left side
		if (Arrays.asList(placeHolderlist.get(0)).contains(
				(Integer) droppableView.getId())) {

			isOnLeftSide = true;

		}
		// Check whether the place holder(droppableView) is in right side
		else if (Arrays.asList(placeHolderlist.get(1)).contains(
				droppableView.getId())) {

			isOnLeftSide = false;

		} else {
			Log.e(TAG_ERROR,
					"Position is undefined of the place holder imageView !");
		}

		return isOnLeftSide;
	}

	/**
	 * Helper class for generating drag shadow
	 */
	private static class TagDragShadowBuilder extends View.DragShadowBuilder {

		// Defines the constructor for TagDragShadowBuilder
		public TagDragShadowBuilder(View v) {

			// Stores the View parameter passed to TagDragShadowBuilder.
			super(v);

		}

		/*
		 * Defines a callback that sends the drag shadow dimensions and touch
		 * point back to the system.
		 */
		@Override
		public void onProvideShadowMetrics(Point size, Point touch) {

			int width;
			int height;

			// Sets the width of the shadow with respect to original View
			width = (getView().getWidth()) * 3;

			// Sets the height of the shadow with respect to original View
			height = (getView().getHeight()) * 3;

			// Sets the size parameter's width and height values. These get back
			// to the system through the size parameter.
			size.set(width, height);

			// Sets the touch point's position to the middle point visual
			// clarity
			touch.set((width / 4), (height / 4));
		}

		/*
		 * Defines a callback that draws the drag shadow in a Canvas that the
		 * system constructs from the dimensions passed in
		 * onProvideShadowMetrics().
		 */
		@Override
		public void onDrawShadow(Canvas canvas) {

			// Draws the ColorDrawable in the Canvas passed in from the system.
			// Scale the canvas 1.5x
			canvas.scale(1.5f, 1.5f);
			getView().draw(canvas);
		}
	}

	@Override
	public void onClick(View tagView) {
		// TODO Auto-generated method stub
		switch (tagView.getId()) {
		case R.id.irisTag:
			InfoTooltip popup = new InfoTooltip(getApplicationContext(), "Iris has spesialized muscles that changes the size of the pupil");
			popup.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.pupilTag:
			InfoTooltip popup1 = new InfoTooltip(getApplicationContext(), "As the light continues it passes through the pupil \n a round opening of the center of the iris.");
			popup1.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.lensTag:
			InfoTooltip popup2 = new InfoTooltip(getApplicationContext(), "It attached to muscles which contract or\n relax inorder to change the lens shape.  ");
			popup2.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.corneaTag:
			InfoTooltip popup3 = new InfoTooltip(getApplicationContext(), "Light first passes through cornea \n Let light comes into the eye.. ");
			popup3.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.vitreousTag:
			InfoTooltip popup4 = new InfoTooltip(getApplicationContext(), "back portion of the eye that is filled\n with a clear, jelly-like substance");
			popup4.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.ciliaryTag:
			InfoTooltip popup5 = new InfoTooltip(getApplicationContext(), "Your message here.. Your message here..  Your message here..  Your message here.. ");
			popup5.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.nerveTag:
			InfoTooltip popup6 = new InfoTooltip(getApplicationContext(), "Your message here.. Your message here..\nYour message here..  Your message here.. ");
			popup6.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.opticdiskTag:
			InfoTooltip popup7 = new InfoTooltip(getApplicationContext(), "Your message here.. Your message here..  Your message here..  Your message here.. ");
			popup7.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.foveaTag:
			InfoTooltip popup8 = new InfoTooltip(getApplicationContext(), "Your message here.. Your message her..\n ANd yit hfsdun hadsfh isdi");
			popup8.show(tagView, AlignMode.BOTTOM);
			break;
		case R.id.retinaTag:
			InfoTooltip popup9 = new InfoTooltip(getApplicationContext(), "The light finally reaches the retina where \n rod and cone cells are stimulated  converting the light to electrical impulese.");
			popup9.show(tagView, AlignMode.BOTTOM);
			break;
		default:
			break;
		}
	}

}