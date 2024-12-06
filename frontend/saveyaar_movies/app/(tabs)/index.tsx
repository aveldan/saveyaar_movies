import React from "react";
import { View } from "react-native";
import {  useRouter } from "expo-router";
import { usePathname } from "expo-router";
import Animated, { interpolate, useAnimatedProps, useAnimatedScrollHandler, useSharedValue, withTiming } from "react-native-reanimated";

import AnimatedHeader from "@/components/AnimatedHeader";
import TabScreenWrapper from "../../components/TabScreenWrapper";
import movieData from "@/data/movies.json";
import MovieList, { MoviesData } from "@/components/MovieList";
import { styles } from "@/styles";

export default function HomeScreen() {

	const pathname = usePathname();
	const isActive = pathname === "/" || pathname == "/index";
	const scrollDirection = useSharedValue(0);

	const SCROLL_THRESHOLD = 4;
	const SLIDE_ACTIVATION_POINT = 90;
	const scrollY = useSharedValue(0);
	const lastScrollY = useSharedValue(0);

	const headerAnimatedProps = useAnimatedProps(() => {
		return {
		  intensity: interpolate(
			scrollY.value,
			[0, 90],
			[0, 85],
			'clamp'
		  )
		};
	});

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
	
	const { movies } = movieData as MoviesData;
	
	return (
		<TabScreenWrapper isActive={isActive} slideDirection="right">
			<View style={styles.container}>
				<AnimatedHeader 
					headerAnimatedProps={headerAnimatedProps}
					scrollDirection={scrollDirection} 
					handleProfile={handleProfile}
					handleNotification={handleNotification}
				/>
				<Animated.ScrollView 
					style={styles.scrollView} 
					contentContainerStyle={styles.scrollViewContent}
					onScroll={scrollHandler}
					scrollEventThrottle={16}
					showsVerticalScrollIndicator={false}
          			bounces={false}
				>
					{movies.map(row => (
						<MovieList key={row.rowTitle} {...row} />
					))}
				</Animated.ScrollView>
			</View>
		</TabScreenWrapper>
	);
};