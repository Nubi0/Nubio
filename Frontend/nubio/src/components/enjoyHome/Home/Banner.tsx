import React from 'react';
import { BannerWrapper, BannerImg } from "../../../styles/SEnjoyHomePage";
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';

const Banner = () => {
    const swiperParams = {
        spaceBetween: 24,
        slidesPerView: 1,
      };
    const bannersUrl = [
        {
            url: process.env.REACT_APP_BANNER1_URL
        },
        {
            url: process.env.REACT_APP_BANNER2_URL,
        }    
    ]
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
