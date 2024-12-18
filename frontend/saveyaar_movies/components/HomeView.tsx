import React, { useState } from "react";
import { View } from "react-native";

import MovieList, { MoviesData } from "@/components/MovieList";
import MidTabComponent from "@/components/MidTabComponent";
import FeaturedContent from "@/components/FeaturedContent";

interface HomeView{
	movieData: MoviesData | null,
	midTab1: any,
	midTab2: any
};

export default function HomeView ({movieData, midTab1, midTab2}: HomeView) {
    

	const handleOnPressFeaturedContent = () => {

	};

    return (
        <View>
            <MidTabComponent tabs={midTab1} />
            {movieData && 
                <FeaturedContent 
                    imageUrl={movieData.featured.backdrop_path}
                    handleOnPress={handleOnPressFeaturedContent}
                />
            }
            <MidTabComponent tabs={midTab2} whiteline />
            {movieData && 
                movieData.movies.map(row => (
                    <MovieList key={row.rowTitle} {...row} />
                ))
            }
        </View>
    )
}