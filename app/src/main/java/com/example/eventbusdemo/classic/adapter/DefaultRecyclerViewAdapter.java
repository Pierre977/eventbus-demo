package com.example.eventbusdemo.classic.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.classic.callback.FragmentChangeCallback;
import com.example.eventbusdemo.classic.callback.ToolbarColorChangeCallback;
import com.example.eventbusdemo.dataset.Color;
import com.example.eventbusdemo.dataset.InnerFragment;

import java.util.List;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public class DefaultRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int COLOR_CHANGE_TYPE = 1;

    private static final int FRAGMENT_CHANGE_TYPE = 2;

    private List<Color> colorList;

    private ToolbarColorChangeCallback colorChangeCallback;

    private FragmentChangeCallback fragmentChangeCallback;

    public DefaultRecyclerViewAdapter(List<Color> colorList, ToolbarColorChangeCallback colorChangeCallback, FragmentChangeCallback fragmentChangeCallback) {
        this.colorList = colorList;
        this.colorChangeCallback = colorChangeCallback;
        this.fragmentChangeCallback = fragmentChangeCallback;
    }

    protected class FragmentItemViewHolder extends RecyclerView.ViewHolder {
        protected Button fragmentChangeButton;

        public FragmentItemViewHolder(View view) {
            super(view);
            fragmentChangeButton = (Button) view.findViewById(R.id.fragment_change_button);
        }
    }

    protected class ColorChangeViewHolder extends RecyclerView.ViewHolder {
        protected RelativeLayout rootLayout;
        protected TextView colorLabel;
        protected Button colorChangeButton;

        public ColorChangeViewHolder(View view) {
            super(view);
            rootLayout = (RelativeLayout) view.findViewById(R.id.root_layout);
            colorLabel = (TextView) view.findViewById(R.id.color_text);
            colorChangeButton = (Button) view.findViewById(R.id.color_change_button);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == COLOR_CHANGE_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_color_item, parent, false);
            viewHolder = new ColorChangeViewHolder(view);
        } else if (viewType == FRAGMENT_CHANGE_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment_item, parent, false);
            viewHolder = new FragmentItemViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position < colorList.size()) {
            final Color color = colorList.get(position);
            ((ColorChangeViewHolder)holder).rootLayout.setBackgroundColor(color.getBackgroundColor());
            ((ColorChangeViewHolder)holder).colorLabel.setTextColor(color.getTextColor());
            ((ColorChangeViewHolder)holder).colorLabel.setText(color.name());
            ((ColorChangeViewHolder)holder).colorChangeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    colorChangeCallback.onColorChange(color);
                }
            });
        } else if (position == colorList.size()) {
            setupFragmentChangeButton((FragmentItemViewHolder) holder, InnerFragment.SECOND);
        } else if (position == colorList.size() + 1) {
            setupFragmentChangeButton((FragmentItemViewHolder) holder, InnerFragment.THIRD);
        }
    }

    private void setupFragmentChangeButton(FragmentItemViewHolder holder, final InnerFragment innerFragment) {
        holder.fragmentChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentChangeCallback.onNextFragment(innerFragment);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        int type = COLOR_CHANGE_TYPE;
        if (position == colorList.size() || position == colorList.size() + 1) {
            type = FRAGMENT_CHANGE_TYPE;
        }

        return type;
    }

    @Override
    public int getItemCount() {
        return colorList.size() + 2;
    }
}
