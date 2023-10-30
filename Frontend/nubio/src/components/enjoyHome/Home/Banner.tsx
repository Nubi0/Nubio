import React from "react";
import { BannerWrapper, BannerImg } from "../../../styles/SEnjoyHomePage";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";

const Banner = () => {
  const swiperParams = {
    spaceBetween: 24,
    slidesPerView: 1,
  };
  const bannersUrl = [
    {
      url: process.env.PUBLIC_URL + "/assets/course/banner/banner1.png",
    },
    {
      url: process.env.PUBLIC_URL + "/assets/course/banner/Banner3.svg",
    },
  ];
  return (
    <BannerWrapper>
      <Swiper {...swiperParams}>
        {bannersUrl.map((value: { url: string | undefined }, index: number) => (
          <SwiperSlide key={index}>
            <BannerImg src={value.url} alt={`Banner ${index + 1}`} />
          </SwiperSlide>
        ))}
      </Swiper>
    </BannerWrapper>
  );
};

export default Banner;
