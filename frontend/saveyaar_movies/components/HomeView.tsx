import React, { useState } from "react";
import { View } from "react-native";

import MovieList, { MoviesData } from "@/components/MovieList";
import MidTabComponent from "@/components/MidTabComponent";
import FeaturedContent from "@/components/FeaturedContent";

export default function HomeView ({featured, movies}: MoviesData) {
    
    const handlePressMidTab1 = (text: string) => {
		setMidTab1((prevTabs) => (
			prevTabs.map((tab) => (
				tab.text === text? {...tab, isActive: true} : {...tab, isActive: false}
			))
		));
	};

	const [midTab1, setMidTab1] = useState([
		{
			text: "Movies",
			handleOnPress: () => handlePressMidTab1("Movies"),
			isActive: true,
		},
		{
			text: "TV Shows",
			handleOnPress: () => handlePressMidTab1("TV Shows"),
			isActive: false,
		}
	]);


	const handleOnPressMidTab2 = (text: string) => {
		setMidTab2((prevTabs) => (
			prevTabs.map((tab) => (
				tab.text === text ? {...tab, isActive: true} : {...tab, isActive: false}
			))
		));
	}

	const [midTab2, setMidTab2] = useState([
		{
			text: "This Week",
			handleOnPress: () => handleOnPressMidTab2("This Week"),
			isActive: false,
		},
		{
			text: "This Month",
			handleOnPress: () => handleOnPressMidTab2("This Month"),
			isActive: true
		},
		{
			text: "Upcoming",
			handleOnPress: () => handleOnPressMidTab2("Upcoming"),
			isActive: false,
		}
	]);

	const handleOnPressFeaturedContent = () => {

	};

    return (
        <View>
            <MidTabComponent tabs={midTab1} />
            <FeaturedContent 
                imageUrl={featured.backdrop_path}
                handleOnPress={handleOnPressFeaturedContent}
            />
            <MidTabComponent tabs={midTab2} whiteline />
            {movies.map(row => (
                <MovieList key={row.rowTitle} {...row} />
            ))}
        </View>
    )
}