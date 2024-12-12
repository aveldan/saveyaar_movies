import React from "react";
import { StyleSheet } from "react-native";
import { usePathname } from "expo-router";

import TabScreenWrapper from "../../../components/TabScreenWrapper";
import HeaderComponent from "@/components/HeaderComponent";
import HomeView from "@/components/HomeView";
import movieData from "@/data/movies.json";
import { MoviesData } from "@/components/MovieList";
import Animated, { useAnimatedScrollHandler, useSharedValue, withTiming } from "react-native-reanimated";

export default function MySubscriptionsScreen() {

	const pathname = usePathname();
	const isActive = pathname === "/my_subscriptions";
	
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

	const { movies } = movieData as MoviesData;

	return (
		<TabScreenWrapper isActive={isActive} slideDirection="left">
			<Animated.ScrollView
				style={styles.scrollView}
				onScroll={scrollHandler}
				scrollEventThrottle={16}
				showsVerticalScrollIndicator={false}
				bounces={false}
			>
				<HeaderComponent title="Subscriptions"/>
				<HomeView movies={movies} />
			</Animated.ScrollView>
		</TabScreenWrapper>
	);
};

const styles = StyleSheet.create({
	scrollView: {
        flex: 1,
        backgroundColor: 'transparent',
        flexDirection: 'column',
    },
});