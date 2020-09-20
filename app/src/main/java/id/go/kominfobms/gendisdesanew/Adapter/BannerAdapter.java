package id.go.kominfobms.gendisdesanew.Adapter;

import id.go.kominfobms.gendisdesanew.Model.BannerModel;
import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class BannerAdapter extends SliderAdapter {
    private BannerModel list;

    public BannerAdapter(BannerModel list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.getData().size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        viewHolder.bindImageSlide(list.getData().get(position));
    }
}
