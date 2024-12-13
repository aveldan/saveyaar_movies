import React, { useState } from "react";
import {  useRouter } from "expo-router";
import { usePathname } from "expo-router";
import Animated, {  useAnimatedScrollHandler, useSharedValue, withTiming } from "react-native-reanimated";

import TabScreenWrapper from "../../components/TabScreenWrapper";
import movieData from "@/data/data.json";
import { MoviesData } from "@/components/MovieList";
import { styles } from "@/styles";
import HomeScreenHeader from "@/components/HomeScreenHeader";
import HomeView from "@/components/HomeView";

export default function HomeScreen() {

	const pathname = usePathname();
	const isActive = pathname === "/" || pathname == "/index";
	const scrollDirection = useSharedValue(0);

	const SCROLL_THRESHOLD = 4;
	const SLIDE_ACTIVATION_POINT = 90;
	const scrollY = useSharedValue(0);
	const lastScrollY = useSharedValue(0);

	const scrollHandler = useAnimatedScrollHandler({
		onScroll: (event) => {
		  const currentScrollY = event.contentOffset.y;
		  const scrollDelta = currentScrollY - lastScrollY.value;
	
		  // Only trigger direction change if we've scrolled past SLIDE_ACTIVATION_POINT
		  if (currentScrollY >= SLIDE_ACTIVATION_POINT) {
			if (scrollDelta > SCROLL_THRESHOLD) {
			  // Scrolling down - hide tabs
			  scrollDirection.value = withTiming(1, { duration: 400 });
			} else if (scrollDelta < -SCROLL_THRESHOLD) {
			  // Scrolling up - show tabs
			  scrollDirection.value = withTiming(0, { duration: 400 });
			}
		  } else {
			// Before SLIDE_ACTIVATION_POINT, always show tabs
			scrollDirection.value = withTiming(0, { duration: 400 });
		  }
	
		  lastScrollY.value = currentScrollY;
		  scrollY.value = currentScrollY;
		},
	  });
	  
	const router = useRouter();
	const handleProfile = () => {
		router.push("/userProfile");
    };

	const handleNotification = () => {
		router.push("/notifications")
	};
	
	const { featured, movies } = movieData as MoviesData;
	
	return (
		<TabScreenWrapper isActive={isActive} slideDirection="right">
			<Animated.ScrollView 
				style={styles.scrollView} 
				onScroll={scrollHandler}
				scrollEventThrottle={16}
				showsVerticalScrollIndicator={false}
				bounces={false}
			>
				<HomeScreenHeader 
					handleProfile={handleProfile}
					handleNotification={handleNotification}
				/>
				<HomeView featured={featured} movies={movies} />
			</Animated.ScrollView>
		</TabScreenWrapper>
	);
};