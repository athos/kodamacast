CLJSBUILD = lein cljsbuild once

.PHONY: all service

all: service

service:
		@cd $@ && $(CLJSBUILD) dev
		@bin/bundle.sh $@
